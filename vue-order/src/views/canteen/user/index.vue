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
      <el-form-item label="username" label-width="80px" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="username"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="password" label-width="80px" prop="password">-->
<!--        <el-input-->
<!--          v-model="queryParams.password"-->
<!--          placeholder="password"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="role" prop="role">
        <el-input
          v-model="queryParams.role"
          placeholder="role"
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
      :data="userList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" v-if="true" />
      <el-table-column label="username" align="center" prop="username" />
<!--      <el-table-column label="password" align="center" prop="password" />-->
      <el-table-column label="role" align="center" prop="role" />
      <el-table-column
        label="addtime "
        align="center"
        prop="addtime"
        width="180"
      />
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
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
      @size-change="sizeChange"
      @current-change="currentChange"
    />

    <!-- 添加或修改用户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="username" label-width="90px" prop="username">
          <el-input v-model="form.username" placeholder="username" />
        </el-form-item>
        <el-form-item label="password" label-width="90px" prop="password">
          <el-input v-model="form.password" placeholder="password" />
        </el-form-item>
        <el-form-item v-if="edit" label="role" label-width="90px" prop="role">
          <el-select v-model="form.role" placeholder="Please select a username">
            <el-option
              v-for="item in users"
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
  listUser,
  getUser,
  delUser,
  addUser,
  updateUser,
} from "@/api/canteens/user.js";

export default {
  name: "User",
  data() {
    return {
      users: [
        {
          value: "user",
          label: "user",
        },
      ],
      width: 0,
      edit: false,
      // 设备名称
      equipmentName: [],
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
      // 用户表格数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: undefined,
        username: undefined,
        password: undefined,
        role: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        username: [
          {
            required: true,
            message: "The username cannot be empty",
            trigger: "blur",
          },
          {
            min: 5,
            max: 12,
            message: "username length must be between 5 and 12",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "The password cannot be empty",
            trigger: "blur",
          },
          {
            min: 5,
            max: 12,
            message: "password length must be between 5 and 12",
            trigger: "blur",
          },
        ],
        role: [
          {
            required: true,
            message: "The role cannot be empty",
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
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then((response) => {
        // 调用过滤函数
        const filteredData = this.filterData(this.queryParams, response.data);
        response.data = filteredData;
        response.data.forEach((v) => {
          const date = new Date(v.addtime);
          const Y = date.getFullYear();
          const M =
            date.getMonth() + 1 < 10
              ? "0" + (date.getMonth() + 1)
              : date.getMonth() + 1;
          const D = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
          const H =
            date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
          const F =
            date.getMinutes() < 10
              ? "0" + date.getMinutes()
              : date.getMinutes();
          const S =
            date.getSeconds() < 10
              ? "0" + date.getSeconds()
              : date.getSeconds();
          const time = Y + "-" + M + "-" + D + " " + H + ":" + F + ":" + S;
          v.addtime = time;
        });
        this.userList = response.data.slice(
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
        username: undefined,
        password: undefined,
        role: undefined,
        addtime: undefined,
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
      this.ids = selection.map((item) => item.username);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "Add user";
      this.edit = true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      this.edit = false;
      const username = row.username || this.ids;
      getUser(username).then((response) => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "Modify user";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.id != null) {
            updateUser(this.form)
              .then((response) => {
                this.$modal.msgSuccess("modify successfully");
                this.open = false;
                this.getList();
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          } else {
            addUser(this.form)
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
      const userIds = row.id || this.ids;
      if (row.role == "Admin") {
        this.$modal.msgError("cannot delete an administrator account");
        return;
      }
      this.$modal
        .confirm("Confirm deletion？")
        .then(() => {
          this.loading = true;
          return delUser(userIds);
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
