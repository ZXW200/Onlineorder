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
      <el-form-item label="orderID" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="orderID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label-width="100px" label="menuitemID" prop="menuItemId">
        <el-input
          v-model="queryParams.menuItemId"
          placeholder="menuitemID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="count" prop="quantity">
        <el-input
          v-model="queryParams.quantity"
          placeholder="count"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="price" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="price"
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

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >编辑</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row> -->

    <el-table
      :style="{ width: width + 'px' }"
      v-loading="loading"
      :data="detailsList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" v-if="true" />
      <el-table-column label="orderID" align="center" prop="orderId" />
      <el-table-column label="menuitemID" align="center" prop="menuItemId" />
      <el-table-column label="count" align="center" prop="quantity" />
      <el-table-column label="price" align="center" prop="price" />
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

    <!-- 添加或修改订单详细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="订单id" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单id" />
        </el-form-item>
        <el-form-item label="menuitemID" prop="menuItemId">
          <el-input v-model="form.menuItemId" placeholder="请输入菜单项id" />
        </el-form-item> -->
        <el-form-item label="count" prop="quantity">
          <el-input v-model="form.quantity" placeholder="Please enter count" />
        </el-form-item>
        <el-form-item label="price" prop="price">
          <el-input v-model="form.price" placeholder="Please enter price" />
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
  listDetails,
  getDetails,
  delDetails,
  addDetails,
  updateDetails,
} from "../../../api/canteens/details.js";

export default {
  data() {
    return {
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
      // 订单详细表格数据
      detailsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: undefined,
        menuItemId: undefined,
        quantity: undefined,
        price: undefined,
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
        orderId: [
          {
            required: true,
            message: "The order id cannot be empty",
            trigger: "blur",
          },
        ],
        menuItemId: [
          {
            required: true,
            message: "The menu item id cannot be empty",
            trigger: "blur",
          },
        ],
        quantity: [
          {
            required: true,
            message: "The count cannot be empty",
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
      },
    };
  },
  created() {
    this.getList();
    this.listener();
  },
  methods: {
    /** 查询订单详细列表 */
    getList() {
      this.loading = true;

      let id = this.$route.query.row;
      this.queryParams.id = id;
      // let orderDetails = JSON.parse(localStorage.getItem("orderDetails"));
      listDetails(this.queryParams).then((response) => {
        this.detailsList = response.data.slice(
          (this.queryParams.pageNum - 1) * this.queryParams.pageSize,
          this.queryParams.pageNum * this.queryParams.pageSize
        );
        this.total = response.data.length;
        this.loading = false;
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
        orderId: undefined,
        menuItemId: undefined,
        quantity: undefined,
        price: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
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
      this.title = "Add order details";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const id = row.id || this.ids;
      getDetails(id).then((response) => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "Modify order details";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.id != null) {
            updateDetails(this.form)
              .then((response) => {
                this.$modal.msgSuccess("modify successfully");
                this.open = false;
                this.getList();
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          } else {
            addDetails(this.form)
              .then((response) => {
                this.$modal.msgSuccess("Added success");
                this.open = false;
                this.getList();
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
      const ids = row.id || this.ids;
      this.$modal
        .confirm("Confirm deletion？")
        .then(() => {
          this.loading = true;
          return delDetails(ids);
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
