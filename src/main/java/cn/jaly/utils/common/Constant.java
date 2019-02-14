package cn.jaly.utils.common;

public class Constant {

    public static final String CURRENT_USER = "current_user";         // 当前用户ID
    public static final String CURRENT_ADMIN = "current_admin";       // 当前管理员ID
    public static final String CURRENT_SITE = "current_site";         // 当前站点ID

    public static final int STATUS_NO_RESPONSE = 0;      // 服务器无响应
    public static final int STATUS_PHONE_REPEAT = 1;     // 该手机号已注册
    public static final int STATUS_PHONE_INVALID = 2;    // 该手机号不合法
    public static final int STATUS_USERNAME_REPEAT = 3;  // 用户名重名
    public static final int STATUS_LOGIN_ERROR = 4;      // 登录失败，账号或密码错误
    public static final int STATUS_USER_OFFLINE = 5;     // 用户已经下线
    public static final int STATUS_CODE_INVALID = 6;     // 验证码输入错误
    public static final int STATUS_USER_ISLOCK = 7;      // 用户被锁定
    public static final int STATUS_UNKNOW_ERROR = 8;     // 未知错误
    public static final int STATUS_SUCCESS = 99;          // 操作成功
    public static final int STATUS_FAILED = -1;           // 操作失败

    public static final int MEMBER_VERIFY_PASS = 1;      // 审核通过
    public static final int MEMBER_VERIFY_REFUSE = 2;    // 申请被拒绝
    public static final int MEMBER_VERIFY_IGNORE = 3;    // 申请被忽略
    public static final int MEMBER_VERIFY_ERROR = 4;     // 审核无法通过

    public static final byte CONTENT_VERIFY_PASS = 99;    // 稿件通过
    public static final byte CONTENT_VERIFY_REJECT = 0;   // 申请被拒绝
    public static final byte CONTENT_VERIFY_FIRST = 1;    // 1审通过
    public static final byte CONTENT_VERIFY_SECOND = 2;   // 2审通过
    public static final byte CONTENT_VERIFY_THIRD = 3;    // 3审通过
    public static final byte CONTENT_VERIFY_FORTH = 4;    // 4审通过

    public static final int CATEGORY_TYPE_NORMAL = 1;    // 常规栏目
    public static final int CATEGORY_TYPE_SINGLE = 2;    // 单个网页
    public static final int CATEGORY_TYPE_LINK = 3;      // 外部链接

}
