import { $_checkOldPwd } from "@/apis/user";
//自定义验证规则函数
//原密码验证
export const checkPwd = (_this) => {
    return async (rule, value, callback) => {
        //检查旧密码是否正确
        const res = await $_checkOldPwd({ oldPwd: value })

        if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]+$/.test(value)) {
            callback(new Error("密码格式必须是数字和字母的组合"));
        } else if (res.data.code != 200) {
            callback(new Error("旧密码错误,请输入正确的密码"))
        } else {
            //触发新密码的验证规则,防止密码一样
            _this.ruleForm.checkPass &&
                _this.$refs.ruleForm.validateField("checkPass");
            callback();
        }
    }
};

//新密码验证
export const checkNewPwd = (_this) => (rule, value, callback) => {
    if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]+$/.test(value)) {
        //判断密码格式
        callback(new Error("密码格式必须是数字和字母的组合"));
    } else if (value === _this.ruleForm.pass) {
        //判断新旧密码不能一致
        callback(new Error("新密码和原密码不能一样"));
    } else {
        _this.ruleForm.notarizePass &&
            _this.$refs.ruleForm.validateField("notarizePass");
        //验证通过
        callback();
    }
};

//确认新密码验证
export const checkConfirmNewPwd = (_this) => (rule, value, callback) => {
    if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]+$/.test(value)) {
        //判断密码格式
        callback(new Error("密码格式必须是数字和字母的组合"));
    } else if (value !== _this.ruleForm.checkPass) {
        //判断新旧密码不能一致
        callback(new Error("新密码和确认新密码必须一致!!!"));
    } else {
        //验证通过
        callback();
    }
};

