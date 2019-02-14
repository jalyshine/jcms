package cn.jaly.comment.entity;

import java.util.ArrayList;
import java.util.List;

public class CommentSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentSettingExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSiteIdIsNull() {
            addCriterion("site_id is null");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNotNull() {
            addCriterion("site_id is not null");
            return (Criteria) this;
        }

        public Criteria andSiteIdEqualTo(Integer value) {
            addCriterion("site_id =", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotEqualTo(Integer value) {
            addCriterion("site_id <>", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThan(Integer value) {
            addCriterion("site_id >", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("site_id >=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThan(Integer value) {
            addCriterion("site_id <", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("site_id <=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIn(List<Integer> values) {
            addCriterion("site_id in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotIn(List<Integer> values) {
            addCriterion("site_id not in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("site_id between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("site_id not between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andAllowGuestIsNull() {
            addCriterion("allow_guest is null");
            return (Criteria) this;
        }

        public Criteria andAllowGuestIsNotNull() {
            addCriterion("allow_guest is not null");
            return (Criteria) this;
        }

        public Criteria andAllowGuestEqualTo(Boolean value) {
            addCriterion("allow_guest =", value, "allowGuest");
            return (Criteria) this;
        }

        public Criteria andAllowGuestNotEqualTo(Boolean value) {
            addCriterion("allow_guest <>", value, "allowGuest");
            return (Criteria) this;
        }

        public Criteria andAllowGuestGreaterThan(Boolean value) {
            addCriterion("allow_guest >", value, "allowGuest");
            return (Criteria) this;
        }

        public Criteria andAllowGuestGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_guest >=", value, "allowGuest");
            return (Criteria) this;
        }

        public Criteria andAllowGuestLessThan(Boolean value) {
            addCriterion("allow_guest <", value, "allowGuest");
            return (Criteria) this;
        }

        public Criteria andAllowGuestLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_guest <=", value, "allowGuest");
            return (Criteria) this;
        }

        public Criteria andAllowGuestIn(List<Boolean> values) {
            addCriterion("allow_guest in", values, "allowGuest");
            return (Criteria) this;
        }

        public Criteria andAllowGuestNotIn(List<Boolean> values) {
            addCriterion("allow_guest not in", values, "allowGuest");
            return (Criteria) this;
        }

        public Criteria andAllowGuestBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_guest between", value1, value2, "allowGuest");
            return (Criteria) this;
        }

        public Criteria andAllowGuestNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_guest not between", value1, value2, "allowGuest");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyIsNull() {
            addCriterion("need_verify is null");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyIsNotNull() {
            addCriterion("need_verify is not null");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyEqualTo(Boolean value) {
            addCriterion("need_verify =", value, "needVerify");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyNotEqualTo(Boolean value) {
            addCriterion("need_verify <>", value, "needVerify");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyGreaterThan(Boolean value) {
            addCriterion("need_verify >", value, "needVerify");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("need_verify >=", value, "needVerify");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyLessThan(Boolean value) {
            addCriterion("need_verify <", value, "needVerify");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyLessThanOrEqualTo(Boolean value) {
            addCriterion("need_verify <=", value, "needVerify");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyIn(List<Boolean> values) {
            addCriterion("need_verify in", values, "needVerify");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyNotIn(List<Boolean> values) {
            addCriterion("need_verify not in", values, "needVerify");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyBetween(Boolean value1, Boolean value2) {
            addCriterion("need_verify between", value1, value2, "needVerify");
            return (Criteria) this;
        }

        public Criteria andNeedVerifyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("need_verify not between", value1, value2, "needVerify");
            return (Criteria) this;
        }

        public Criteria andNeedCodeIsNull() {
            addCriterion("need_code is null");
            return (Criteria) this;
        }

        public Criteria andNeedCodeIsNotNull() {
            addCriterion("need_code is not null");
            return (Criteria) this;
        }

        public Criteria andNeedCodeEqualTo(Boolean value) {
            addCriterion("need_code =", value, "needCode");
            return (Criteria) this;
        }

        public Criteria andNeedCodeNotEqualTo(Boolean value) {
            addCriterion("need_code <>", value, "needCode");
            return (Criteria) this;
        }

        public Criteria andNeedCodeGreaterThan(Boolean value) {
            addCriterion("need_code >", value, "needCode");
            return (Criteria) this;
        }

        public Criteria andNeedCodeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("need_code >=", value, "needCode");
            return (Criteria) this;
        }

        public Criteria andNeedCodeLessThan(Boolean value) {
            addCriterion("need_code <", value, "needCode");
            return (Criteria) this;
        }

        public Criteria andNeedCodeLessThanOrEqualTo(Boolean value) {
            addCriterion("need_code <=", value, "needCode");
            return (Criteria) this;
        }

        public Criteria andNeedCodeIn(List<Boolean> values) {
            addCriterion("need_code in", values, "needCode");
            return (Criteria) this;
        }

        public Criteria andNeedCodeNotIn(List<Boolean> values) {
            addCriterion("need_code not in", values, "needCode");
            return (Criteria) this;
        }

        public Criteria andNeedCodeBetween(Boolean value1, Boolean value2) {
            addCriterion("need_code between", value1, value2, "needCode");
            return (Criteria) this;
        }

        public Criteria andNeedCodeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("need_code not between", value1, value2, "needCode");
            return (Criteria) this;
        }

        public Criteria andAddPointIsNull() {
            addCriterion("add_point is null");
            return (Criteria) this;
        }

        public Criteria andAddPointIsNotNull() {
            addCriterion("add_point is not null");
            return (Criteria) this;
        }

        public Criteria andAddPointEqualTo(Byte value) {
            addCriterion("add_point =", value, "addPoint");
            return (Criteria) this;
        }

        public Criteria andAddPointNotEqualTo(Byte value) {
            addCriterion("add_point <>", value, "addPoint");
            return (Criteria) this;
        }

        public Criteria andAddPointGreaterThan(Byte value) {
            addCriterion("add_point >", value, "addPoint");
            return (Criteria) this;
        }

        public Criteria andAddPointGreaterThanOrEqualTo(Byte value) {
            addCriterion("add_point >=", value, "addPoint");
            return (Criteria) this;
        }

        public Criteria andAddPointLessThan(Byte value) {
            addCriterion("add_point <", value, "addPoint");
            return (Criteria) this;
        }

        public Criteria andAddPointLessThanOrEqualTo(Byte value) {
            addCriterion("add_point <=", value, "addPoint");
            return (Criteria) this;
        }

        public Criteria andAddPointIn(List<Byte> values) {
            addCriterion("add_point in", values, "addPoint");
            return (Criteria) this;
        }

        public Criteria andAddPointNotIn(List<Byte> values) {
            addCriterion("add_point not in", values, "addPoint");
            return (Criteria) this;
        }

        public Criteria andAddPointBetween(Byte value1, Byte value2) {
            addCriterion("add_point between", value1, value2, "addPoint");
            return (Criteria) this;
        }

        public Criteria andAddPointNotBetween(Byte value1, Byte value2) {
            addCriterion("add_point not between", value1, value2, "addPoint");
            return (Criteria) this;
        }

        public Criteria andDelPointIsNull() {
            addCriterion("del_point is null");
            return (Criteria) this;
        }

        public Criteria andDelPointIsNotNull() {
            addCriterion("del_point is not null");
            return (Criteria) this;
        }

        public Criteria andDelPointEqualTo(Byte value) {
            addCriterion("del_point =", value, "delPoint");
            return (Criteria) this;
        }

        public Criteria andDelPointNotEqualTo(Byte value) {
            addCriterion("del_point <>", value, "delPoint");
            return (Criteria) this;
        }

        public Criteria andDelPointGreaterThan(Byte value) {
            addCriterion("del_point >", value, "delPoint");
            return (Criteria) this;
        }

        public Criteria andDelPointGreaterThanOrEqualTo(Byte value) {
            addCriterion("del_point >=", value, "delPoint");
            return (Criteria) this;
        }

        public Criteria andDelPointLessThan(Byte value) {
            addCriterion("del_point <", value, "delPoint");
            return (Criteria) this;
        }

        public Criteria andDelPointLessThanOrEqualTo(Byte value) {
            addCriterion("del_point <=", value, "delPoint");
            return (Criteria) this;
        }

        public Criteria andDelPointIn(List<Byte> values) {
            addCriterion("del_point in", values, "delPoint");
            return (Criteria) this;
        }

        public Criteria andDelPointNotIn(List<Byte> values) {
            addCriterion("del_point not in", values, "delPoint");
            return (Criteria) this;
        }

        public Criteria andDelPointBetween(Byte value1, Byte value2) {
            addCriterion("del_point between", value1, value2, "delPoint");
            return (Criteria) this;
        }

        public Criteria andDelPointNotBetween(Byte value1, Byte value2) {
            addCriterion("del_point not between", value1, value2, "delPoint");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}