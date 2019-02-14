package cn.jaly.member.entity;

import java.util.ArrayList;
import java.util.List;

public class MemberSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberSettingExample() {
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

        public Criteria andAllowRegIsNull() {
            addCriterion("allow_reg is null");
            return (Criteria) this;
        }

        public Criteria andAllowRegIsNotNull() {
            addCriterion("allow_reg is not null");
            return (Criteria) this;
        }

        public Criteria andAllowRegEqualTo(Boolean value) {
            addCriterion("allow_reg =", value, "allowReg");
            return (Criteria) this;
        }

        public Criteria andAllowRegNotEqualTo(Boolean value) {
            addCriterion("allow_reg <>", value, "allowReg");
            return (Criteria) this;
        }

        public Criteria andAllowRegGreaterThan(Boolean value) {
            addCriterion("allow_reg >", value, "allowReg");
            return (Criteria) this;
        }

        public Criteria andAllowRegGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_reg >=", value, "allowReg");
            return (Criteria) this;
        }

        public Criteria andAllowRegLessThan(Boolean value) {
            addCriterion("allow_reg <", value, "allowReg");
            return (Criteria) this;
        }

        public Criteria andAllowRegLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_reg <=", value, "allowReg");
            return (Criteria) this;
        }

        public Criteria andAllowRegIn(List<Boolean> values) {
            addCriterion("allow_reg in", values, "allowReg");
            return (Criteria) this;
        }

        public Criteria andAllowRegNotIn(List<Boolean> values) {
            addCriterion("allow_reg not in", values, "allowReg");
            return (Criteria) this;
        }

        public Criteria andAllowRegBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_reg between", value1, value2, "allowReg");
            return (Criteria) this;
        }

        public Criteria andAllowRegNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_reg not between", value1, value2, "allowReg");
            return (Criteria) this;
        }

        public Criteria andSelectModelIsNull() {
            addCriterion("select_model is null");
            return (Criteria) this;
        }

        public Criteria andSelectModelIsNotNull() {
            addCriterion("select_model is not null");
            return (Criteria) this;
        }

        public Criteria andSelectModelEqualTo(Boolean value) {
            addCriterion("select_model =", value, "selectModel");
            return (Criteria) this;
        }

        public Criteria andSelectModelNotEqualTo(Boolean value) {
            addCriterion("select_model <>", value, "selectModel");
            return (Criteria) this;
        }

        public Criteria andSelectModelGreaterThan(Boolean value) {
            addCriterion("select_model >", value, "selectModel");
            return (Criteria) this;
        }

        public Criteria andSelectModelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("select_model >=", value, "selectModel");
            return (Criteria) this;
        }

        public Criteria andSelectModelLessThan(Boolean value) {
            addCriterion("select_model <", value, "selectModel");
            return (Criteria) this;
        }

        public Criteria andSelectModelLessThanOrEqualTo(Boolean value) {
            addCriterion("select_model <=", value, "selectModel");
            return (Criteria) this;
        }

        public Criteria andSelectModelIn(List<Boolean> values) {
            addCriterion("select_model in", values, "selectModel");
            return (Criteria) this;
        }

        public Criteria andSelectModelNotIn(List<Boolean> values) {
            addCriterion("select_model not in", values, "selectModel");
            return (Criteria) this;
        }

        public Criteria andSelectModelBetween(Boolean value1, Boolean value2) {
            addCriterion("select_model between", value1, value2, "selectModel");
            return (Criteria) this;
        }

        public Criteria andSelectModelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("select_model not between", value1, value2, "selectModel");
            return (Criteria) this;
        }

        public Criteria andNeedEmailIsNull() {
            addCriterion("need_email is null");
            return (Criteria) this;
        }

        public Criteria andNeedEmailIsNotNull() {
            addCriterion("need_email is not null");
            return (Criteria) this;
        }

        public Criteria andNeedEmailEqualTo(Boolean value) {
            addCriterion("need_email =", value, "needEmail");
            return (Criteria) this;
        }

        public Criteria andNeedEmailNotEqualTo(Boolean value) {
            addCriterion("need_email <>", value, "needEmail");
            return (Criteria) this;
        }

        public Criteria andNeedEmailGreaterThan(Boolean value) {
            addCriterion("need_email >", value, "needEmail");
            return (Criteria) this;
        }

        public Criteria andNeedEmailGreaterThanOrEqualTo(Boolean value) {
            addCriterion("need_email >=", value, "needEmail");
            return (Criteria) this;
        }

        public Criteria andNeedEmailLessThan(Boolean value) {
            addCriterion("need_email <", value, "needEmail");
            return (Criteria) this;
        }

        public Criteria andNeedEmailLessThanOrEqualTo(Boolean value) {
            addCriterion("need_email <=", value, "needEmail");
            return (Criteria) this;
        }

        public Criteria andNeedEmailIn(List<Boolean> values) {
            addCriterion("need_email in", values, "needEmail");
            return (Criteria) this;
        }

        public Criteria andNeedEmailNotIn(List<Boolean> values) {
            addCriterion("need_email not in", values, "needEmail");
            return (Criteria) this;
        }

        public Criteria andNeedEmailBetween(Boolean value1, Boolean value2) {
            addCriterion("need_email between", value1, value2, "needEmail");
            return (Criteria) this;
        }

        public Criteria andNeedEmailNotBetween(Boolean value1, Boolean value2) {
            addCriterion("need_email not between", value1, value2, "needEmail");
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

        public Criteria andNeedPhoneIsNull() {
            addCriterion("need_phone is null");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneIsNotNull() {
            addCriterion("need_phone is not null");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneEqualTo(Boolean value) {
            addCriterion("need_phone =", value, "needPhone");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneNotEqualTo(Boolean value) {
            addCriterion("need_phone <>", value, "needPhone");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneGreaterThan(Boolean value) {
            addCriterion("need_phone >", value, "needPhone");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneGreaterThanOrEqualTo(Boolean value) {
            addCriterion("need_phone >=", value, "needPhone");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneLessThan(Boolean value) {
            addCriterion("need_phone <", value, "needPhone");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneLessThanOrEqualTo(Boolean value) {
            addCriterion("need_phone <=", value, "needPhone");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneIn(List<Boolean> values) {
            addCriterion("need_phone in", values, "needPhone");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneNotIn(List<Boolean> values) {
            addCriterion("need_phone not in", values, "needPhone");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneBetween(Boolean value1, Boolean value2) {
            addCriterion("need_phone between", value1, value2, "needPhone");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneNotBetween(Boolean value1, Boolean value2) {
            addCriterion("need_phone not between", value1, value2, "needPhone");
            return (Criteria) this;
        }

        public Criteria andNeedAdminIsNull() {
            addCriterion("need_admin is null");
            return (Criteria) this;
        }

        public Criteria andNeedAdminIsNotNull() {
            addCriterion("need_admin is not null");
            return (Criteria) this;
        }

        public Criteria andNeedAdminEqualTo(Boolean value) {
            addCriterion("need_admin =", value, "needAdmin");
            return (Criteria) this;
        }

        public Criteria andNeedAdminNotEqualTo(Boolean value) {
            addCriterion("need_admin <>", value, "needAdmin");
            return (Criteria) this;
        }

        public Criteria andNeedAdminGreaterThan(Boolean value) {
            addCriterion("need_admin >", value, "needAdmin");
            return (Criteria) this;
        }

        public Criteria andNeedAdminGreaterThanOrEqualTo(Boolean value) {
            addCriterion("need_admin >=", value, "needAdmin");
            return (Criteria) this;
        }

        public Criteria andNeedAdminLessThan(Boolean value) {
            addCriterion("need_admin <", value, "needAdmin");
            return (Criteria) this;
        }

        public Criteria andNeedAdminLessThanOrEqualTo(Boolean value) {
            addCriterion("need_admin <=", value, "needAdmin");
            return (Criteria) this;
        }

        public Criteria andNeedAdminIn(List<Boolean> values) {
            addCriterion("need_admin in", values, "needAdmin");
            return (Criteria) this;
        }

        public Criteria andNeedAdminNotIn(List<Boolean> values) {
            addCriterion("need_admin not in", values, "needAdmin");
            return (Criteria) this;
        }

        public Criteria andNeedAdminBetween(Boolean value1, Boolean value2) {
            addCriterion("need_admin between", value1, value2, "needAdmin");
            return (Criteria) this;
        }

        public Criteria andNeedAdminNotBetween(Boolean value1, Boolean value2) {
            addCriterion("need_admin not between", value1, value2, "needAdmin");
            return (Criteria) this;
        }

        public Criteria andIntegralModelIsNull() {
            addCriterion("integral_model is null");
            return (Criteria) this;
        }

        public Criteria andIntegralModelIsNotNull() {
            addCriterion("integral_model is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralModelEqualTo(Boolean value) {
            addCriterion("integral_model =", value, "integralModel");
            return (Criteria) this;
        }

        public Criteria andIntegralModelNotEqualTo(Boolean value) {
            addCriterion("integral_model <>", value, "integralModel");
            return (Criteria) this;
        }

        public Criteria andIntegralModelGreaterThan(Boolean value) {
            addCriterion("integral_model >", value, "integralModel");
            return (Criteria) this;
        }

        public Criteria andIntegralModelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("integral_model >=", value, "integralModel");
            return (Criteria) this;
        }

        public Criteria andIntegralModelLessThan(Boolean value) {
            addCriterion("integral_model <", value, "integralModel");
            return (Criteria) this;
        }

        public Criteria andIntegralModelLessThanOrEqualTo(Boolean value) {
            addCriterion("integral_model <=", value, "integralModel");
            return (Criteria) this;
        }

        public Criteria andIntegralModelIn(List<Boolean> values) {
            addCriterion("integral_model in", values, "integralModel");
            return (Criteria) this;
        }

        public Criteria andIntegralModelNotIn(List<Boolean> values) {
            addCriterion("integral_model not in", values, "integralModel");
            return (Criteria) this;
        }

        public Criteria andIntegralModelBetween(Boolean value1, Boolean value2) {
            addCriterion("integral_model between", value1, value2, "integralModel");
            return (Criteria) this;
        }

        public Criteria andIntegralModelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("integral_model not between", value1, value2, "integralModel");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceIsNull() {
            addCriterion("integral_price is null");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceIsNotNull() {
            addCriterion("integral_price is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceEqualTo(Float value) {
            addCriterion("integral_price =", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceNotEqualTo(Float value) {
            addCriterion("integral_price <>", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceGreaterThan(Float value) {
            addCriterion("integral_price >", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("integral_price >=", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceLessThan(Float value) {
            addCriterion("integral_price <", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceLessThanOrEqualTo(Float value) {
            addCriterion("integral_price <=", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceIn(List<Float> values) {
            addCriterion("integral_price in", values, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceNotIn(List<Float> values) {
            addCriterion("integral_price not in", values, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceBetween(Float value1, Float value2) {
            addCriterion("integral_price between", value1, value2, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceNotBetween(Float value1, Float value2) {
            addCriterion("integral_price not between", value1, value2, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andInitPointIsNull() {
            addCriterion("init_point is null");
            return (Criteria) this;
        }

        public Criteria andInitPointIsNotNull() {
            addCriterion("init_point is not null");
            return (Criteria) this;
        }

        public Criteria andInitPointEqualTo(Integer value) {
            addCriterion("init_point =", value, "initPoint");
            return (Criteria) this;
        }

        public Criteria andInitPointNotEqualTo(Integer value) {
            addCriterion("init_point <>", value, "initPoint");
            return (Criteria) this;
        }

        public Criteria andInitPointGreaterThan(Integer value) {
            addCriterion("init_point >", value, "initPoint");
            return (Criteria) this;
        }

        public Criteria andInitPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("init_point >=", value, "initPoint");
            return (Criteria) this;
        }

        public Criteria andInitPointLessThan(Integer value) {
            addCriterion("init_point <", value, "initPoint");
            return (Criteria) this;
        }

        public Criteria andInitPointLessThanOrEqualTo(Integer value) {
            addCriterion("init_point <=", value, "initPoint");
            return (Criteria) this;
        }

        public Criteria andInitPointIn(List<Integer> values) {
            addCriterion("init_point in", values, "initPoint");
            return (Criteria) this;
        }

        public Criteria andInitPointNotIn(List<Integer> values) {
            addCriterion("init_point not in", values, "initPoint");
            return (Criteria) this;
        }

        public Criteria andInitPointBetween(Integer value1, Integer value2) {
            addCriterion("init_point between", value1, value2, "initPoint");
            return (Criteria) this;
        }

        public Criteria andInitPointNotBetween(Integer value1, Integer value2) {
            addCriterion("init_point not between", value1, value2, "initPoint");
            return (Criteria) this;
        }

        public Criteria andInitMoneyIsNull() {
            addCriterion("init_money is null");
            return (Criteria) this;
        }

        public Criteria andInitMoneyIsNotNull() {
            addCriterion("init_money is not null");
            return (Criteria) this;
        }

        public Criteria andInitMoneyEqualTo(Float value) {
            addCriterion("init_money =", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyNotEqualTo(Float value) {
            addCriterion("init_money <>", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyGreaterThan(Float value) {
            addCriterion("init_money >", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyGreaterThanOrEqualTo(Float value) {
            addCriterion("init_money >=", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyLessThan(Float value) {
            addCriterion("init_money <", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyLessThanOrEqualTo(Float value) {
            addCriterion("init_money <=", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyIn(List<Float> values) {
            addCriterion("init_money in", values, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyNotIn(List<Float> values) {
            addCriterion("init_money not in", values, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyBetween(Float value1, Float value2) {
            addCriterion("init_money between", value1, value2, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyNotBetween(Float value1, Float value2) {
            addCriterion("init_money not between", value1, value2, "initMoney");
            return (Criteria) this;
        }

        public Criteria andShowAgreeIsNull() {
            addCriterion("show_agree is null");
            return (Criteria) this;
        }

        public Criteria andShowAgreeIsNotNull() {
            addCriterion("show_agree is not null");
            return (Criteria) this;
        }

        public Criteria andShowAgreeEqualTo(Boolean value) {
            addCriterion("show_agree =", value, "showAgree");
            return (Criteria) this;
        }

        public Criteria andShowAgreeNotEqualTo(Boolean value) {
            addCriterion("show_agree <>", value, "showAgree");
            return (Criteria) this;
        }

        public Criteria andShowAgreeGreaterThan(Boolean value) {
            addCriterion("show_agree >", value, "showAgree");
            return (Criteria) this;
        }

        public Criteria andShowAgreeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("show_agree >=", value, "showAgree");
            return (Criteria) this;
        }

        public Criteria andShowAgreeLessThan(Boolean value) {
            addCriterion("show_agree <", value, "showAgree");
            return (Criteria) this;
        }

        public Criteria andShowAgreeLessThanOrEqualTo(Boolean value) {
            addCriterion("show_agree <=", value, "showAgree");
            return (Criteria) this;
        }

        public Criteria andShowAgreeIn(List<Boolean> values) {
            addCriterion("show_agree in", values, "showAgree");
            return (Criteria) this;
        }

        public Criteria andShowAgreeNotIn(List<Boolean> values) {
            addCriterion("show_agree not in", values, "showAgree");
            return (Criteria) this;
        }

        public Criteria andShowAgreeBetween(Boolean value1, Boolean value2) {
            addCriterion("show_agree between", value1, value2, "showAgree");
            return (Criteria) this;
        }

        public Criteria andShowAgreeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("show_agree not between", value1, value2, "showAgree");
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