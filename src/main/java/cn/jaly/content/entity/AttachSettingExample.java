package cn.jaly.content.entity;

import java.util.ArrayList;
import java.util.List;

public class AttachSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AttachSettingExample() {
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

        public Criteria andLimitSizeIsNull() {
            addCriterion("limit_size is null");
            return (Criteria) this;
        }

        public Criteria andLimitSizeIsNotNull() {
            addCriterion("limit_size is not null");
            return (Criteria) this;
        }

        public Criteria andLimitSizeEqualTo(Integer value) {
            addCriterion("limit_size =", value, "limitSize");
            return (Criteria) this;
        }

        public Criteria andLimitSizeNotEqualTo(Integer value) {
            addCriterion("limit_size <>", value, "limitSize");
            return (Criteria) this;
        }

        public Criteria andLimitSizeGreaterThan(Integer value) {
            addCriterion("limit_size >", value, "limitSize");
            return (Criteria) this;
        }

        public Criteria andLimitSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_size >=", value, "limitSize");
            return (Criteria) this;
        }

        public Criteria andLimitSizeLessThan(Integer value) {
            addCriterion("limit_size <", value, "limitSize");
            return (Criteria) this;
        }

        public Criteria andLimitSizeLessThanOrEqualTo(Integer value) {
            addCriterion("limit_size <=", value, "limitSize");
            return (Criteria) this;
        }

        public Criteria andLimitSizeIn(List<Integer> values) {
            addCriterion("limit_size in", values, "limitSize");
            return (Criteria) this;
        }

        public Criteria andLimitSizeNotIn(List<Integer> values) {
            addCriterion("limit_size not in", values, "limitSize");
            return (Criteria) this;
        }

        public Criteria andLimitSizeBetween(Integer value1, Integer value2) {
            addCriterion("limit_size between", value1, value2, "limitSize");
            return (Criteria) this;
        }

        public Criteria andLimitSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_size not between", value1, value2, "limitSize");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionIsNull() {
            addCriterion("allow_extension is null");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionIsNotNull() {
            addCriterion("allow_extension is not null");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionEqualTo(String value) {
            addCriterion("allow_extension =", value, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionNotEqualTo(String value) {
            addCriterion("allow_extension <>", value, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionGreaterThan(String value) {
            addCriterion("allow_extension >", value, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionGreaterThanOrEqualTo(String value) {
            addCriterion("allow_extension >=", value, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionLessThan(String value) {
            addCriterion("allow_extension <", value, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionLessThanOrEqualTo(String value) {
            addCriterion("allow_extension <=", value, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionLike(String value) {
            addCriterion("allow_extension like", value, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionNotLike(String value) {
            addCriterion("allow_extension not like", value, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionIn(List<String> values) {
            addCriterion("allow_extension in", values, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionNotIn(List<String> values) {
            addCriterion("allow_extension not in", values, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionBetween(String value1, String value2) {
            addCriterion("allow_extension between", value1, value2, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andAllowExtensionNotBetween(String value1, String value2) {
            addCriterion("allow_extension not between", value1, value2, "allowExtension");
            return (Criteria) this;
        }

        public Criteria andEnableMarkIsNull() {
            addCriterion("enable_mark is null");
            return (Criteria) this;
        }

        public Criteria andEnableMarkIsNotNull() {
            addCriterion("enable_mark is not null");
            return (Criteria) this;
        }

        public Criteria andEnableMarkEqualTo(Boolean value) {
            addCriterion("enable_mark =", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkNotEqualTo(Boolean value) {
            addCriterion("enable_mark <>", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkGreaterThan(Boolean value) {
            addCriterion("enable_mark >", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkGreaterThanOrEqualTo(Boolean value) {
            addCriterion("enable_mark >=", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkLessThan(Boolean value) {
            addCriterion("enable_mark <", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkLessThanOrEqualTo(Boolean value) {
            addCriterion("enable_mark <=", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkIn(List<Boolean> values) {
            addCriterion("enable_mark in", values, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkNotIn(List<Boolean> values) {
            addCriterion("enable_mark not in", values, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkBetween(Boolean value1, Boolean value2) {
            addCriterion("enable_mark between", value1, value2, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkNotBetween(Boolean value1, Boolean value2) {
            addCriterion("enable_mark not between", value1, value2, "enableMark");
            return (Criteria) this;
        }

        public Criteria andMarkWidthIsNull() {
            addCriterion("mark_width is null");
            return (Criteria) this;
        }

        public Criteria andMarkWidthIsNotNull() {
            addCriterion("mark_width is not null");
            return (Criteria) this;
        }

        public Criteria andMarkWidthEqualTo(Integer value) {
            addCriterion("mark_width =", value, "markWidth");
            return (Criteria) this;
        }

        public Criteria andMarkWidthNotEqualTo(Integer value) {
            addCriterion("mark_width <>", value, "markWidth");
            return (Criteria) this;
        }

        public Criteria andMarkWidthGreaterThan(Integer value) {
            addCriterion("mark_width >", value, "markWidth");
            return (Criteria) this;
        }

        public Criteria andMarkWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("mark_width >=", value, "markWidth");
            return (Criteria) this;
        }

        public Criteria andMarkWidthLessThan(Integer value) {
            addCriterion("mark_width <", value, "markWidth");
            return (Criteria) this;
        }

        public Criteria andMarkWidthLessThanOrEqualTo(Integer value) {
            addCriterion("mark_width <=", value, "markWidth");
            return (Criteria) this;
        }

        public Criteria andMarkWidthIn(List<Integer> values) {
            addCriterion("mark_width in", values, "markWidth");
            return (Criteria) this;
        }

        public Criteria andMarkWidthNotIn(List<Integer> values) {
            addCriterion("mark_width not in", values, "markWidth");
            return (Criteria) this;
        }

        public Criteria andMarkWidthBetween(Integer value1, Integer value2) {
            addCriterion("mark_width between", value1, value2, "markWidth");
            return (Criteria) this;
        }

        public Criteria andMarkWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("mark_width not between", value1, value2, "markWidth");
            return (Criteria) this;
        }

        public Criteria andMarkHeightIsNull() {
            addCriterion("mark_height is null");
            return (Criteria) this;
        }

        public Criteria andMarkHeightIsNotNull() {
            addCriterion("mark_height is not null");
            return (Criteria) this;
        }

        public Criteria andMarkHeightEqualTo(Integer value) {
            addCriterion("mark_height =", value, "markHeight");
            return (Criteria) this;
        }

        public Criteria andMarkHeightNotEqualTo(Integer value) {
            addCriterion("mark_height <>", value, "markHeight");
            return (Criteria) this;
        }

        public Criteria andMarkHeightGreaterThan(Integer value) {
            addCriterion("mark_height >", value, "markHeight");
            return (Criteria) this;
        }

        public Criteria andMarkHeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("mark_height >=", value, "markHeight");
            return (Criteria) this;
        }

        public Criteria andMarkHeightLessThan(Integer value) {
            addCriterion("mark_height <", value, "markHeight");
            return (Criteria) this;
        }

        public Criteria andMarkHeightLessThanOrEqualTo(Integer value) {
            addCriterion("mark_height <=", value, "markHeight");
            return (Criteria) this;
        }

        public Criteria andMarkHeightIn(List<Integer> values) {
            addCriterion("mark_height in", values, "markHeight");
            return (Criteria) this;
        }

        public Criteria andMarkHeightNotIn(List<Integer> values) {
            addCriterion("mark_height not in", values, "markHeight");
            return (Criteria) this;
        }

        public Criteria andMarkHeightBetween(Integer value1, Integer value2) {
            addCriterion("mark_height between", value1, value2, "markHeight");
            return (Criteria) this;
        }

        public Criteria andMarkHeightNotBetween(Integer value1, Integer value2) {
            addCriterion("mark_height not between", value1, value2, "markHeight");
            return (Criteria) this;
        }

        public Criteria andMarkIsNull() {
            addCriterion("mark is null");
            return (Criteria) this;
        }

        public Criteria andMarkIsNotNull() {
            addCriterion("mark is not null");
            return (Criteria) this;
        }

        public Criteria andMarkEqualTo(String value) {
            addCriterion("mark =", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotEqualTo(String value) {
            addCriterion("mark <>", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThan(String value) {
            addCriterion("mark >", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThanOrEqualTo(String value) {
            addCriterion("mark >=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThan(String value) {
            addCriterion("mark <", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThanOrEqualTo(String value) {
            addCriterion("mark <=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLike(String value) {
            addCriterion("mark like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotLike(String value) {
            addCriterion("mark not like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkIn(List<String> values) {
            addCriterion("mark in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotIn(List<String> values) {
            addCriterion("mark not in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkBetween(String value1, String value2) {
            addCriterion("mark between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotBetween(String value1, String value2) {
            addCriterion("mark not between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityIsNull() {
            addCriterion("mark_opacity is null");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityIsNotNull() {
            addCriterion("mark_opacity is not null");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityEqualTo(Integer value) {
            addCriterion("mark_opacity =", value, "markOpacity");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityNotEqualTo(Integer value) {
            addCriterion("mark_opacity <>", value, "markOpacity");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityGreaterThan(Integer value) {
            addCriterion("mark_opacity >", value, "markOpacity");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityGreaterThanOrEqualTo(Integer value) {
            addCriterion("mark_opacity >=", value, "markOpacity");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityLessThan(Integer value) {
            addCriterion("mark_opacity <", value, "markOpacity");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityLessThanOrEqualTo(Integer value) {
            addCriterion("mark_opacity <=", value, "markOpacity");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityIn(List<Integer> values) {
            addCriterion("mark_opacity in", values, "markOpacity");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityNotIn(List<Integer> values) {
            addCriterion("mark_opacity not in", values, "markOpacity");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityBetween(Integer value1, Integer value2) {
            addCriterion("mark_opacity between", value1, value2, "markOpacity");
            return (Criteria) this;
        }

        public Criteria andMarkOpacityNotBetween(Integer value1, Integer value2) {
            addCriterion("mark_opacity not between", value1, value2, "markOpacity");
            return (Criteria) this;
        }

        public Criteria andMarkQualityIsNull() {
            addCriterion("mark_quality is null");
            return (Criteria) this;
        }

        public Criteria andMarkQualityIsNotNull() {
            addCriterion("mark_quality is not null");
            return (Criteria) this;
        }

        public Criteria andMarkQualityEqualTo(Integer value) {
            addCriterion("mark_quality =", value, "markQuality");
            return (Criteria) this;
        }

        public Criteria andMarkQualityNotEqualTo(Integer value) {
            addCriterion("mark_quality <>", value, "markQuality");
            return (Criteria) this;
        }

        public Criteria andMarkQualityGreaterThan(Integer value) {
            addCriterion("mark_quality >", value, "markQuality");
            return (Criteria) this;
        }

        public Criteria andMarkQualityGreaterThanOrEqualTo(Integer value) {
            addCriterion("mark_quality >=", value, "markQuality");
            return (Criteria) this;
        }

        public Criteria andMarkQualityLessThan(Integer value) {
            addCriterion("mark_quality <", value, "markQuality");
            return (Criteria) this;
        }

        public Criteria andMarkQualityLessThanOrEqualTo(Integer value) {
            addCriterion("mark_quality <=", value, "markQuality");
            return (Criteria) this;
        }

        public Criteria andMarkQualityIn(List<Integer> values) {
            addCriterion("mark_quality in", values, "markQuality");
            return (Criteria) this;
        }

        public Criteria andMarkQualityNotIn(List<Integer> values) {
            addCriterion("mark_quality not in", values, "markQuality");
            return (Criteria) this;
        }

        public Criteria andMarkQualityBetween(Integer value1, Integer value2) {
            addCriterion("mark_quality between", value1, value2, "markQuality");
            return (Criteria) this;
        }

        public Criteria andMarkQualityNotBetween(Integer value1, Integer value2) {
            addCriterion("mark_quality not between", value1, value2, "markQuality");
            return (Criteria) this;
        }

        public Criteria andMarkPositionIsNull() {
            addCriterion("mark_position is null");
            return (Criteria) this;
        }

        public Criteria andMarkPositionIsNotNull() {
            addCriterion("mark_position is not null");
            return (Criteria) this;
        }

        public Criteria andMarkPositionEqualTo(Byte value) {
            addCriterion("mark_position =", value, "markPosition");
            return (Criteria) this;
        }

        public Criteria andMarkPositionNotEqualTo(Byte value) {
            addCriterion("mark_position <>", value, "markPosition");
            return (Criteria) this;
        }

        public Criteria andMarkPositionGreaterThan(Byte value) {
            addCriterion("mark_position >", value, "markPosition");
            return (Criteria) this;
        }

        public Criteria andMarkPositionGreaterThanOrEqualTo(Byte value) {
            addCriterion("mark_position >=", value, "markPosition");
            return (Criteria) this;
        }

        public Criteria andMarkPositionLessThan(Byte value) {
            addCriterion("mark_position <", value, "markPosition");
            return (Criteria) this;
        }

        public Criteria andMarkPositionLessThanOrEqualTo(Byte value) {
            addCriterion("mark_position <=", value, "markPosition");
            return (Criteria) this;
        }

        public Criteria andMarkPositionIn(List<Byte> values) {
            addCriterion("mark_position in", values, "markPosition");
            return (Criteria) this;
        }

        public Criteria andMarkPositionNotIn(List<Byte> values) {
            addCriterion("mark_position not in", values, "markPosition");
            return (Criteria) this;
        }

        public Criteria andMarkPositionBetween(Byte value1, Byte value2) {
            addCriterion("mark_position between", value1, value2, "markPosition");
            return (Criteria) this;
        }

        public Criteria andMarkPositionNotBetween(Byte value1, Byte value2) {
            addCriterion("mark_position not between", value1, value2, "markPosition");
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