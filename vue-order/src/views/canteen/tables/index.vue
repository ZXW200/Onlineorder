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
      <el-form-item label="id" prop="id">
        <el-input-number
          v-model="queryParams.id"
          placeholder="id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label-width="120px"
        label="seatingCapacity"
        prop="seatingCapacity"
      >
        <el-input
          v-model="queryParams.seatingCapacity"
          placeholder="seatingCapacity"
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
      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      -->
    </el-row>

    <el-table
      :style="{ width: width + 'px' }"
      v-loading="loading"
      :data="tablesList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" v-if="true" />
      <el-table-column label="peopleNumber" align="center" prop="number" />
      <el-table-column
        label="seatingCapacity"
        align="center"
        prop="seatingCapacity"
      />
      <el-table-column label="status" align="center" prop="status" />
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

    <!-- 添加或修改餐桌对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="peopleNumber" prop="number">
          <el-input v-model="form.number" placeholder="peopleNumber" />
        </el-form-item>
        <el-form-item label="seatingCapacity" prop="seatingCapacity">
          <el-input
            v-model="form.seatingCapacity"
            placeholder="seatingCapacity"
          />
        </el-form-item>
        <el-form-item label="status" prop="status ">
          <el-select v-model="form.status" placeholder="status">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
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
  listTables,
  getTables,
  delTables,
  addTables,
  updateTables,
} from "../../../api/canteens/tables.js";

export default {
  name: "Tables",
  data() {
    return {
      options: [
        {
          value: 0,
          label: "AVAILABLE",
        },
        {
          value: 1,
          label: "OCCUPIED",
        },
        {
          value: 2,
          label: "RESERVED",
        },
        {
          value: 3,
          label: "OUT_OF_SERVICE",
        },
      ],
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
      // 餐桌表格数据
      tablesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id:undefined,
        peopleNumber: undefined,
        seatingCapacity: undefined,
        status: undefined,
      },
      width: 0,
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
        peopleNumber: [
          {
            required: true,
            message: "the count cannot be empty",
            trigger: "blur",
          },
        ],
        seatingCapacity: [
          {
            required: true,
            message: "The number of seats cannot be empty",
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
  filterData(criteria, data) {
    console.log(criteria); // Helpful for debugging
    return data.filter((item) => {
      // Iterate over the search criteria
      for (const key in criteria) {
        // Ignore pageNum and pageSize as they are used for pagination, not filtering
        if (key === "pageNum" || key === "pageSize") continue;

        // Ensure the item has the key to avoid undefined issues
        if (!item.hasOwnProperty(key)) continue;

        const value = criteria[key];
        let itemValue = item[key];

        // Handle different types of criteria
        switch (typeof value) {
          case "string":
            // If the criteria is a string, check if the item's value contains the criteria
            // Convert both to lower case for case-insensitive comparison
            if (itemValue && typeof itemValue === "string" && !itemValue.toLowerCase().includes(value.toLowerCase())) {
              return false;
            }
            break;
          case "number":
            // If the criteria is a number, check for equality
            // Convert itemValue to a number in case it's a string representation
            if (typeof itemValue === "string") itemValue = Number(itemValue);
            if (itemValue !== value) {
              return false;
            }
            break;
          case "boolean":
            // If the criteria is a boolean, check for equality
            // Assume itemValue is stored as a boolean, no need for conversion
            if (itemValue !== value) {
              return false;
            }
            break;
          default:
            // Add custom logic for other types if necessary
            break;
        }
      }
      // If all criteria are met, return true
      return true;
    });
    },
    /** 查询餐桌列表 */
    getList() {
      this.loading = true;
      listTables(this.queryParams).then((response) => {
        // 调用过滤函数
        const filteredData = this.filterData(this.queryParams, response.data);
        response.data = filteredData;
        // this.tablesList = response.data
        this.tablesList = response.data.slice(
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
        number: undefined,
        seatingCapacity: undefined,
        status: undefined,
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
      this.title = "Add dining table";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const id = row.id || this.ids;
      getTables(id).then((response) => {
        console.log(response, 111);
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "Change the table";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.id != null) {
            updateTables(this.form)
              .then((response) => {
                this.$modal.msgSuccess("modify successfully");
                this.open = false;
                this.getList();
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          } else {
            addTables(this.form)
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
          return delTables(ids);
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
