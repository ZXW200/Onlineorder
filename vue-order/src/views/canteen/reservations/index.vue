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
      <el-form-item label="tableID" prop="tableId">
        <el-input
          v-model="queryParams.tableId"
          placeholder="tableID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label-width="130px"
        label="reservationTime"
        prop="reservationTime"
      >
        <el-date-picker
          clearable
          v-model="queryParams.reservationTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="reservationTime"
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
        >编辑</el-button>
      </el-col>
    </el-row> -->

    <el-table
      :style="{ width: width + 'px' }"
      v-loading="loading"
      :data="reservationsList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" v-if="true" />
      <el-table-column label="userID" align="center" prop="userId" />
      <el-table-column label="tableID" align="center" prop="tableId" />
      <el-table-column
        label="reservationTime"
        align="center"
        prop="reservationTime"
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

    <!-- 添加或修改预定对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="userID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="tableID" prop="tableId">
          <el-input v-model="form.tableId" placeholder="请输入餐桌id" />
        </el-form-item> -->
        <el-form-item label-width="120px" label="status" prop="status">
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
        <el-form-item
          label-width="120px"
          label="Booking time"
          prop="reservationTime"
        >
          <el-date-picker
            clearable
            v-model="form.reservationTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="Booking time"
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
  listReservations,
  getReservations,
  delReservations,
  addReservations,
  updateReservations,
} from "../../../api/canteens/reservations.js";

export default {
  name: "Reservations",
  data() {
    return {
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
          label: "CANCELLED",
        },
        {
          value: 3,
          label: "COMPLETED",
        },
      ],
      // 按钮loading
      buttonLoading: false,
      // 遮罩层
      loading: true,
      width: 0,
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
      // 预定表格数据
      reservationsList: [],
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
        reservationTime: undefined,
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
        reservationTime: [
          {
            required: true,
            message: "Booking time cannot be empty",
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
    this.getList();
    this.listener();
  },
  methods: {
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
    /** 查询预定列表 */
    getList() {
      this.loading = true;
      listReservations(this.queryParams).then((response) => {
        // 调用过滤函数
        const filteredData = this.filterData(this.queryParams, response.data);
        response.data = filteredData;
        // this.reservationsList = response;
        // this.reservationsList = response.data
        this.reservationsList = response.data.slice(
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
        reservationTime: undefined,
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
      this.title = "Add reservation";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const id = row.id || this.ids;
      getReservations(id).then((response) => {
        console.log(response);
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "Modify reservation";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.id != null) {
            updateReservations(this.form)
              .then((response) => {
                console.log(this.form);
                this.$modal.msgSuccess("modify successfully");
                this.open = false;
                this.getList();
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          } else {
            addReservations(this.form)
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
          return delReservations(ids);
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
