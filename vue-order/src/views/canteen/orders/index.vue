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
      <el-form-item label="userID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="userID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="orderTime" label-width="100px" prop="orderTime">
        <el-date-picker
          clearable
          v-model="queryParams.orderTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="orderTime"
        >
        </el-date-picker>
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

    <el-table
      :style="{ width: width + 'px' }"
      v-loading="loading"
      :data="ordersList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" v-if="true" />
      <el-table-column label="userID" align="center" prop="userId" />
      <el-table-column label="tableID" align="center" prop="tableId" />
      <el-table-column
        label="orderTime"
        align="center"
        prop="orderTime"
        width="180"
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

    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="userID" prop="userId">
          <el-input v-model="form.userId" placeholder="userID" />
        </el-form-item>
        <el-form-item label="tableID" prop="tableId">
          <el-input v-model="form.tableId" placeholder="请输入餐桌id" />
        </el-form-item> -->
        <el-form-item label="status" label-width="100px" prop="status">
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
        <el-form-item label="orderTime" label-width="100px" prop="orderTime">
          <el-date-picker
            clearable
            v-model="form.orderTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="orderTime"
          >
          </el-date-picker>
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
  listOrders,
  getOrders,
  delOrders,
  addOrders,
  updateOrders,
} from "../../../api/canteens/orders.js";
export default {
  name: "Orders",
  data() {
    return {
      width: 0,
      options: [
        {
          value: 0,
          label: "PENDING",
        },
        {
          value: 1,
          label: "CONFIRMED",
        },
        {
          value: 2,
          label: "PREPARING",
        },
        {
          value: 3,
          label: "READY",
        },
        {
          value: 4,
          label: "DELIVERED",
        },
        {
          value: 5,
          label: "CANCELLED",
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
      // 订单表格数据
      ordersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: undefined,
        tableId: undefined,
        orderTime: undefined,
        status: undefined,
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
        userId: [
          {
            required: true,
            message: "The user id cannot be empty",
            trigger: "blur",
          },
        ],
        tableId: [
          {
            required: true,
            message: "The table id cannot be empty",
            trigger: "blur",
          },
        ],
        orderTime: [
          {
            required: true,
            message: "Order time cannot be empty",
            trigger: "blur",
          },
        ],
        status: [
          {
            required: true,
            message: "The status cannot be empty",
            trigger: "change",
          },
        ],
      },
    };
  },
  created() {
    this.listener();
    this.getList();
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
    /** 查询订单列表 */
    getList() {
      this.loading = true;
      listOrders(this.queryParams).then((response) => {
        // 调用过滤函数
        const filteredData = this.filterData(this.queryParams, response.data);
        response.data = filteredData;
        this.ordersList = response.data.slice(
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
        userId: undefined,
        tableId: undefined,
        orderTime: undefined,
        status: undefined,
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
      this.title = "Add order";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      console.log(row, 111);
      this.loading = true;
      this.reset();
      const id = row.id || this.ids;
      getOrders(id).then((response) => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "Modify order";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.id != null) {
            updateOrders(this.form)
              .then((response) => {
                this.$modal.msgSuccess("modify successfully");
                this.open = false;
                this.getList();
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          } else {
            addOrders(this.form)
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
          return delOrders(ids);
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

    // 详情按钮操作
    handleDetail(row) {
      this.$router.push({
        path: "/details",
        query: {
          row: row.id,
        },
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


