<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label-width="100px" label="dishName" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="please enter dishName"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label-width="100px" label="dishDescribe" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="please enter dishDescribe"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label-width="100px" label="dishPrice" prop="price">
        <el-input-number
          v-model="queryParams.price"
          placeholder="please enter dishPrice"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label-width="150px"
        label="dishClassification "
        prop="category"
      >
        <el-input
          v-model="queryParams.category"
          placeholder="please enter dishClassification "
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label-width="100px" label="available" prop="available">
        <el-input
          v-model="queryParams.available"
          placeholder="available"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >Search</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >Reset</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          >Add</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          >Edit</el-button
        >
      </el-col>
    </el-row>

    <el-table
      :style="{ width: width + 'px' }"
      v-loading="loading"
      :data="itemsList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" v-if="true" />
      <el-table-column label="dishName" align="center" prop="name" />
      <el-table-column label="dishDescribe" align="center" prop="description" />
      <el-table-column label="dishPrice" align="center" prop="price" />
      <el-table-column
        label="dishClassification "
        align="center"
        prop="category"
      />
      <el-table-column label="available" align="center" prop="available" />
      <el-table-column
        label="operation"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            >Edit</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            >Delete</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      @size-change="sizeChange"
      @current-change="currentChange"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改菜单项对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label-width="120px" label="dishName" prop="name">
          <el-input v-model="form.name" placeholder="please enter dishName" />
        </el-form-item>
        <el-form-item
          label-width="120px"
          label="dishDescribe"
          prop="description"
        >
          <el-input
            v-model="form.description"
            type="textarea"
            placeholder="please enter dishDescribe"
          />
        </el-form-item>

        <el-form-item label-width="120px" label="dishPrice" prop="price">
          <el-input v-model="form.price" placeholder="please enter dishPrice" />
        </el-form-item>
        <el-form-item label-width="120px" label="category " prop="category">
          <el-select
            v-model="form.category"
            placeholder="please enter category "
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label-width="120px" label="available" prop="available">
          <el-select
            v-model="form.available"
            placeholder="please enter available"
          >
            <el-option
              v-for="item in isAvailable"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="productImage"
          prop="productImage"
          label-width="120px"
        >
          <el-upload
            method="post"
            action="http://localhost:18080/api/menuItems/fileUpload"
            v-model="form.productImage"
            :file-list="fileList"
            :limit="1"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :headers="headerObj"
            list-type="picture"
          >
            <el-button size="small" type="primary">click to upload</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm"
          >Confirm</el-button
        >
        <el-button @click="cancel">Cancel</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listItems,
  getItems,
  delItems,
  addItems,
  updateItems,
} from "../../../api/canteens/items.js";

export default {
  name: "Items",
  data() {
    return {
      options: [
        {
          value: "1",
          label: "coffer",
        },
        {
          value: "2",
          label: "food",
        },
        {
          value: "3",
          label: "dessert",
        },
      ],
      isAvailable: [
        {
          value: "true",
          label: "usable",
        },
        {
          value: "false",
          label: "not applicable",
        },
      ],
      width: 0,
      // 按钮loading
      buttonLoading: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 菜单项表格数据
      itemsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        description: undefined,
        price: undefined,
        category: undefined,
        available: undefined,
        productImage: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          {
            required: true,
            message: "The id cannot be empty",
            trigger: "blur",
          },
        ],
        name: [
          {
            required: true,
            message: "The name cannot be empty",
            trigger: "blur",
          },
        ],
        description: [
          {
            required: true,
            message: "The description cannot be empty",
            trigger: "blur",
          },
        ],
        price: [
          {
            required: true,
            message: "The price cannot be empty",
            trigger: "blur",
          },
        ],
        category: [
          {
            required: true,
            message: "The classify cannot be empty",
            trigger: "blur",
          },
        ],
        available: [
          {
            required: true,
            message: "The available cannot be empty",
            trigger: "blur",
          },
        ],
      },
      imageUrl: "",
      headerObj: {
        Authorization: "bearer " + localStorage.getItem("token"),
      },
      fileList: [],
    };
  },
  created() {
    this.getList();
    this.listener();
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.queryParams.productImage = file.response;
      this.imageUrl = res;
      console.log(res, file);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isPNG = file.type === "image/png";
      const isBMP = file.type === "image/bmp";
      const isGIF = file.type === "image/gif";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!(isJPG || isPNG || isBMP || isGIF)) {
        this.$message.warning("图片格式不正确");
      }
      if (!isLt2M) {
        this.$message.warning("图片大小不能超过 2 MB");
      }
      return (isJPG || isPNG || isBMP || isGIF) && isLt2M;
    },
    productImg(src) {
      return "http://localhost:18080/temp-rainy/" + src;
    },
    filterData(criteria, data) {
      return data.filter((item) => {
        // 遍历搜索条件
        for (const key in criteria) {
          // 忽略 pageNum 和 pageSize，因为它们用于分页而不是过滤
          if (key === "pageNum" || key === "pageSize") continue;

          // 根据类型进行比较
          switch (typeof criteria[key]) {
            case "string":
              // 如果搜索条件是字符串，检查是否包含
              if (
                typeof item[key] === "string" &&
                !(item[key] + "").includes(criteria[key])
              ) {
                return false;
              }
              break;
            case "number":
              // 如果搜索条件是数字，检查是否相等（注意：这里假设价格是字符串，需要转换）
              if (
                typeof item[key] === "number" &&
                item[key] !== Number(criteria[key])
              ) {
                return false;
              }
              break;
            case "boolean":
              // 如果搜索条件是布尔值，检查是否相等（注意：这里假设 available 是字符串，需要转换）
              if (
                typeof item[key] === "boolean" &&
                item[key] !== (criteria[key] === "true")
              ) {
                return false;
              }
              break;
            default:
              // 其他类型可以根据需要添加处理逻辑
              break;
          }
        }
        // 所有条件都满足，返回 true
        return true;
      });
    },
    /** 查询菜单项列表 */
    getList() {
      this.loading = true;
      listItems(this.queryParams).then((response) => {
        // 调用过滤函数
        const filteredData = this.filterData(this.queryParams, response.data);
        response.data = filteredData;
        this.itemsList = response.data.slice(
          (this.queryParams.pageNum - 1) * this.queryParams.pageSize,
          this.queryParams.pageNum * this.queryParams.pageSize
        );
        this.total = response.data.length;
        this.loading = false;
        if (this.itemsList) {
          this.itemsList.forEach((v) => {
            if (v.category == 1) {
              v.category = "coffer";
            } else if (v.category == 2) {
              v.category = "food";
            } else {
              v.category = "dessert";
            }

            if (v.available == true) {
              v.available = "usable";
            } else {
              v.available = "not applicable";
            }
          });
        }
      });
    },
    // page改变的回调
    currentChange(val) {
      this.queryParams.pageNum = val;
      this.getList();
    },
    // size改变的回调
    sizeChange() {
      this.queryParams.pageSize = val;
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        name: undefined,
        description: undefined,
        price: undefined,
        category: undefined,
        available: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        productImage: [],
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "Add menu item";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const id = row.id || this.ids;
      getItems(id).then((response) => {
        console.log(response);
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "Modifying menu items";
        this.imageUrl = response.data.productImage;
        this.queryParams.productImage = response.data.productImage;
        this.fileList = [
          {
            name: response.data.productImage,
            url: this.productImg(response.data.productImage),
          },
        ];
        if (this.form.available === true) {
          this.form.available = "usable";
        } else {
          this.form.available = "not applicable";
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.productImage = this.imageUrl;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.id != null) {
            if (this.form.available === "usable") {
              this.form.available = true;
            }
            if (this.form.available === "not applicable") {
              this.form.available = false;
            }

            updateItems(this.form)
              .then((response) => {
                this.$modal.msgSuccess("modify successfully");
                this.open = false;
                this.getList();
                this.form.productImage = "";
                this.fileList = [];
                this.imageUrl = "";
                this.queryParams.productImage = "";
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          } else {
            addItems(this.form)
              .then((response) => {
                this.$modal.msgSuccess("Added success");
                this.open = false;
                this.getList();
                this.form.productImage = "";
                this.imageUrl = "";
                this.fileList = [];
                this.queryParams.productImage = "";
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      console.log(row);
      const ids = row.id || this.ids;
      console.log(ids);
      this.$modal
        .confirm("Confirm deletion?")
        .then(() => {
          this.loading = true;
          return delItems(ids);
        })
        .then(() => {
          this.loading = false;
          this.getList();
          this.$modal.msgSuccess("successfully delete");
        })
        .catch(() => {})
        .finally(() => {
          this.loading = false;
        });
    },
    listener() {
      //进入页面获取表格宽度
      this.width = document.body.offsetWidth - 160;
      console.log(document.body.offsetWidth);
      // 当屏幕大小移动时，动态获取宽度
      window.addEventListener("resize", this.getWith);
    },
    getWith(e) {
      // 给表格宽度赋值
      this.width = e.target.innerWidth - 160;
    },
  },
  destroyed() {
    window.removeEventListener("resize", this.getWith);
  },
};
</script>
