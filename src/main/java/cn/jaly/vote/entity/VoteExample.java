package cn.jaly.vote.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VoteExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andCreditPointIsNull() {
            addCriterion("credit_point is null");
            return (Criteria) this;
        }

        public Criteria andCreditPointIsNotNull() {
            addCriterion("credit_point is not null");
            return (Criteria) this;
        }

        public Criteria andCreditPointEqualTo(Integer value) {
            addCriterion("credit_point =", value, "creditPoint");
            return (Criteria) this;
        }

        public Criteria andCreditPointNotEqualTo(Integer value) {
            addCriterion("credit_point <>", value, "creditPoint");
            return (Criteria) this;
        }

        public Criteria andCreditPointGreaterThan(Integer value) {
            addCriterion("credit_point >", value, "creditPoint");
            return (Criteria) this;
        }

        public Criteria andCreditPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("credit_point >=", value, "creditPoint");
            return (Criteria) this;
        }

        public Criteria andCreditPointLessThan(Integer value) {
            addCriterion("credit_point <", value, "creditPoint");
            return (Criteria) this;
        }

        public Criteria andCreditPointLessThanOrEqualTo(Integer value) {
            addCriterion("credit_point <=", value, "creditPoint");
            return (Criteria) this;
        }

        public Criteria andCreditPointIn(List<Integer> values) {
            addCriterion("credit_point in", values, "creditPoint");
            return (Criteria) this;
        }

        public Criteria andCreditPointNotIn(List<Integer> values) {
            addCriterion("credit_point not in", values, "creditPoint");
            return (Criteria) this;
        }

        public Criteria andCreditPointBetween(Integer value1, Integer value2) {
            addCriterion("credit_point between", value1, value2, "creditPoint");
            return (Criteria) this;
        }

        public Criteria andCreditPointNotBetween(Integer value1, Integer value2) {
            addCriterion("credit_point not between", value1, value2, "creditPoint");
            return (Criteria) this;
        }

        public Criteria andFromTimeIsNull() {
            addCriterion("from_time is null");
            return (Criteria) this;
        }

        public Criteria andFromTimeIsNotNull() {
            addCriterion("from_time is not null");
            return (Criteria) this;
        }

        public Criteria andFromTimeEqualTo(Date value) {
            addCriterion("from_time =", value, "fromTime");
            return (Criteria) this;
        }

        public Criteria andFromTimeNotEqualTo(Date value) {
            addCriterion("from_time <>", value, "fromTime");
            return (Criteria) this;
        }

        public Criteria andFromTimeGreaterThan(Date value) {
            addCriterion("from_time >", value, "fromTime");
            return (Criteria) this;
        }

        public Criteria andFromTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("from_time >=", value, "fromTime");
            return (Criteria) this;
        }

        public Criteria andFromTimeLessThan(Date value) {
            addCriterion("from_time <", value, "fromTime");
            return (Criteria) this;
        }

        public Criteria andFromTimeLessThanOrEqualTo(Date value) {
            addCriterion("from_time <=", value, "fromTime");
            return (Criteria) this;
        }

        public Criteria andFromTimeIn(List<Date> values) {
            addCriterion("from_time in", values, "fromTime");
            return (Criteria) this;
        }

        public Criteria andFromTimeNotIn(List<Date> values) {
            addCriterion("from_time not in", values, "fromTime");
            return (Criteria) this;
        }

        public Criteria andFromTimeBetween(Date value1, Date value2) {
            addCriterion("from_time between", value1, value2, "fromTime");
            return (Criteria) this;
        }

        public Criteria andFromTimeNotBetween(Date value1, Date value2) {
            addCriterion("from_time not between", value1, value2, "fromTime");
            return (Criteria) this;
        }

        public Criteria andToTimeIsNull() {
            addCriterion("to_time is null");
            return (Criteria) this;
        }

        public Criteria andToTimeIsNotNull() {
            addCriterion("to_time is not null");
            return (Criteria) this;
        }

        public Criteria andToTimeEqualTo(Date value) {
            addCriterion("to_time =", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeNotEqualTo(Date value) {
            addCriterion("to_time <>", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeGreaterThan(Date value) {
            addCriterion("to_time >", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("to_time >=", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeLessThan(Date value) {
            addCriterion("to_time <", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeLessThanOrEqualTo(Date value) {
            addCriterion("to_time <=", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeIn(List<Date> values) {
            addCriterion("to_time in", values, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeNotIn(List<Date> values) {
            addCriterion("to_time not in", values, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeBetween(Date value1, Date value2) {
            addCriterion("to_time between", value1, value2, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeNotBetween(Date value1, Date value2) {
            addCriterion("to_time not between", value1, value2, "toTime");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysIsNull() {
            addCriterion("interval_days is null");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysIsNotNull() {
            addCriterion("interval_days is not null");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysEqualTo(Short value) {
            addCriterion("interval_days =", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysNotEqualTo(Short value) {
            addCriterion("interval_days <>", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysGreaterThan(Short value) {
            addCriterion("interval_days >", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysGreaterThanOrEqualTo(Short value) {
            addCriterion("interval_days >=", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysLessThan(Short value) {
            addCriterion("interval_days <", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysLessThanOrEqualTo(Short value) {
            addCriterion("interval_days <=", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysIn(List<Short> values) {
            addCriterion("interval_days in", values, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysNotIn(List<Short> values) {
            addCriterion("interval_days not in", values, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysBetween(Short value1, Short value2) {
            addCriterion("interval_days between", value1, value2, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysNotBetween(Short value1, Short value2) {
            addCriterion("interval_days not between", value1, value2, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andMaxValIsNull() {
            addCriterion("max_val is null");
            return (Criteria) this;
        }

        public Criteria andMaxValIsNotNull() {
            addCriterion("max_val is not null");
            return (Criteria) this;
        }

        public Criteria andMaxValEqualTo(Short value) {
            addCriterion("max_val =", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValNotEqualTo(Short value) {
            addCriterion("max_val <>", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValGreaterThan(Short value) {
            addCriterion("max_val >", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValGreaterThanOrEqualTo(Short value) {
            addCriterion("max_val >=", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValLessThan(Short value) {
            addCriterion("max_val <", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValLessThanOrEqualTo(Short value) {
            addCriterion("max_val <=", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValIn(List<Short> values) {
            addCriterion("max_val in", values, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValNotIn(List<Short> values) {
            addCriterion("max_val not in", values, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValBetween(Short value1, Short value2) {
            addCriterion("max_val between", value1, value2, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValNotBetween(Short value1, Short value2) {
            addCriterion("max_val not between", value1, value2, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMinValIsNull() {
            addCriterion("min_val is null");
            return (Criteria) this;
        }

        public Criteria andMinValIsNotNull() {
            addCriterion("min_val is not null");
            return (Criteria) this;
        }

        public Criteria andMinValEqualTo(Short value) {
            addCriterion("min_val =", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValNotEqualTo(Short value) {
            addCriterion("min_val <>", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValGreaterThan(Short value) {
            addCriterion("min_val >", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValGreaterThanOrEqualTo(Short value) {
            addCriterion("min_val >=", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValLessThan(Short value) {
            addCriterion("min_val <", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValLessThanOrEqualTo(Short value) {
            addCriterion("min_val <=", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValIn(List<Short> values) {
            addCriterion("min_val in", values, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValNotIn(List<Short> values) {
            addCriterion("min_val not in", values, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValBetween(Short value1, Short value2) {
            addCriterion("min_val between", value1, value2, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValNotBetween(Short value1, Short value2) {
            addCriterion("min_val not between", value1, value2, "minVal");
            return (Criteria) this;
        }

        public Criteria andDisabledIsNull() {
            addCriterion("disabled is null");
            return (Criteria) this;
        }

        public Criteria andDisabledIsNotNull() {
            addCriterion("disabled is not null");
            return (Criteria) this;
        }

        public Criteria andDisabledEqualTo(Boolean value) {
            addCriterion("disabled =", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotEqualTo(Boolean value) {
            addCriterion("disabled <>", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledGreaterThan(Boolean value) {
            addCriterion("disabled >", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("disabled >=", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledLessThan(Boolean value) {
            addCriterion("disabled <", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledLessThanOrEqualTo(Boolean value) {
            addCriterion("disabled <=", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledIn(List<Boolean> values) {
            addCriterion("disabled in", values, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotIn(List<Boolean> values) {
            addCriterion("disabled not in", values, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledBetween(Boolean value1, Boolean value2) {
            addCriterion("disabled between", value1, value2, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("disabled not between", value1, value2, "disabled");
            return (Criteria) this;
        }

        public Criteria andIsMultipleIsNull() {
            addCriterion("is_multiple is null");
            return (Criteria) this;
        }

        public Criteria andIsMultipleIsNotNull() {
            addCriterion("is_multiple is not null");
            return (Criteria) this;
        }

        public Criteria andIsMultipleEqualTo(Boolean value) {
            addCriterion("is_multiple =", value, "isMultiple");
            return (Criteria) this;
        }

        public Criteria andIsMultipleNotEqualTo(Boolean value) {
            addCriterion("is_multiple <>", value, "isMultiple");
            return (Criteria) this;
        }

        public Criteria andIsMultipleGreaterThan(Boolean value) {
            addCriterion("is_multiple >", value, "isMultiple");
            return (Criteria) this;
        }

        public Criteria andIsMultipleGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_multiple >=", value, "isMultiple");
            return (Criteria) this;
        }

        public Criteria andIsMultipleLessThan(Boolean value) {
            addCriterion("is_multiple <", value, "isMultiple");
            return (Criteria) this;
        }

        public Criteria andIsMultipleLessThanOrEqualTo(Boolean value) {
            addCriterion("is_multiple <=", value, "isMultiple");
            return (Criteria) this;
        }

        public Criteria andIsMultipleIn(List<Boolean> values) {
            addCriterion("is_multiple in", values, "isMultiple");
            return (Criteria) this;
        }

        public Criteria andIsMultipleNotIn(List<Boolean> values) {
            addCriterion("is_multiple not in", values, "isMultiple");
            return (Criteria) this;
        }

        public Criteria andIsMultipleBetween(Boolean value1, Boolean value2) {
            addCriterion("is_multiple between", value1, value2, "isMultiple");
            return (Criteria) this;
        }

        public Criteria andIsMultipleNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_multiple not between", value1, value2, "isMultiple");
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

        public Criteria andAllowViewIsNull() {
            addCriterion("allow_view is null");
            return (Criteria) this;
        }

        public Criteria andAllowViewIsNotNull() {
            addCriterion("allow_view is not null");
            return (Criteria) this;
        }

        public Criteria andAllowViewEqualTo(Boolean value) {
            addCriterion("allow_view =", value, "allowView");
            return (Criteria) this;
        }

        public Criteria andAllowViewNotEqualTo(Boolean value) {
            addCriterion("allow_view <>", value, "allowView");
            return (Criteria) this;
        }

        public Criteria andAllowViewGreaterThan(Boolean value) {
            addCriterion("allow_view >", value, "allowView");
            return (Criteria) this;
        }

        public Criteria andAllowViewGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_view >=", value, "allowView");
            return (Criteria) this;
        }

        public Criteria andAllowViewLessThan(Boolean value) {
            addCriterion("allow_view <", value, "allowView");
            return (Criteria) this;
        }

        public Criteria andAllowViewLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_view <=", value, "allowView");
            return (Criteria) this;
        }

        public Criteria andAllowViewIn(List<Boolean> values) {
            addCriterion("allow_view in", values, "allowView");
            return (Criteria) this;
        }

        public Criteria andAllowViewNotIn(List<Boolean> values) {
            addCriterion("allow_view not in", values, "allowView");
            return (Criteria) this;
        }

        public Criteria andAllowViewBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_view between", value1, value2, "allowView");
            return (Criteria) this;
        }

        public Criteria andAllowViewNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_view not between", value1, value2, "allowView");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
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