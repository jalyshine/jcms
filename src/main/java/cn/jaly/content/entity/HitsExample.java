package cn.jaly.content.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HitsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HitsExample() {
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

        public Criteria andHitsIdIsNull() {
            addCriterion("hits_id is null");
            return (Criteria) this;
        }

        public Criteria andHitsIdIsNotNull() {
            addCriterion("hits_id is not null");
            return (Criteria) this;
        }

        public Criteria andHitsIdEqualTo(String value) {
            addCriterion("hits_id =", value, "hitsId");
            return (Criteria) this;
        }

        public Criteria andHitsIdNotEqualTo(String value) {
            addCriterion("hits_id <>", value, "hitsId");
            return (Criteria) this;
        }

        public Criteria andHitsIdGreaterThan(String value) {
            addCriterion("hits_id >", value, "hitsId");
            return (Criteria) this;
        }

        public Criteria andHitsIdGreaterThanOrEqualTo(String value) {
            addCriterion("hits_id >=", value, "hitsId");
            return (Criteria) this;
        }

        public Criteria andHitsIdLessThan(String value) {
            addCriterion("hits_id <", value, "hitsId");
            return (Criteria) this;
        }

        public Criteria andHitsIdLessThanOrEqualTo(String value) {
            addCriterion("hits_id <=", value, "hitsId");
            return (Criteria) this;
        }

        public Criteria andHitsIdLike(String value) {
            addCriterion("hits_id like", value, "hitsId");
            return (Criteria) this;
        }

        public Criteria andHitsIdNotLike(String value) {
            addCriterion("hits_id not like", value, "hitsId");
            return (Criteria) this;
        }

        public Criteria andHitsIdIn(List<String> values) {
            addCriterion("hits_id in", values, "hitsId");
            return (Criteria) this;
        }

        public Criteria andHitsIdNotIn(List<String> values) {
            addCriterion("hits_id not in", values, "hitsId");
            return (Criteria) this;
        }

        public Criteria andHitsIdBetween(String value1, String value2) {
            addCriterion("hits_id between", value1, value2, "hitsId");
            return (Criteria) this;
        }

        public Criteria andHitsIdNotBetween(String value1, String value2) {
            addCriterion("hits_id not between", value1, value2, "hitsId");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Integer value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Integer value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Integer value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Integer value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Integer value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Integer> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Integer> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Integer value1, Integer value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andYesterdayIsNull() {
            addCriterion("yesterday is null");
            return (Criteria) this;
        }

        public Criteria andYesterdayIsNotNull() {
            addCriterion("yesterday is not null");
            return (Criteria) this;
        }

        public Criteria andYesterdayEqualTo(Integer value) {
            addCriterion("yesterday =", value, "yesterday");
            return (Criteria) this;
        }

        public Criteria andYesterdayNotEqualTo(Integer value) {
            addCriterion("yesterday <>", value, "yesterday");
            return (Criteria) this;
        }

        public Criteria andYesterdayGreaterThan(Integer value) {
            addCriterion("yesterday >", value, "yesterday");
            return (Criteria) this;
        }

        public Criteria andYesterdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("yesterday >=", value, "yesterday");
            return (Criteria) this;
        }

        public Criteria andYesterdayLessThan(Integer value) {
            addCriterion("yesterday <", value, "yesterday");
            return (Criteria) this;
        }

        public Criteria andYesterdayLessThanOrEqualTo(Integer value) {
            addCriterion("yesterday <=", value, "yesterday");
            return (Criteria) this;
        }

        public Criteria andYesterdayIn(List<Integer> values) {
            addCriterion("yesterday in", values, "yesterday");
            return (Criteria) this;
        }

        public Criteria andYesterdayNotIn(List<Integer> values) {
            addCriterion("yesterday not in", values, "yesterday");
            return (Criteria) this;
        }

        public Criteria andYesterdayBetween(Integer value1, Integer value2) {
            addCriterion("yesterday between", value1, value2, "yesterday");
            return (Criteria) this;
        }

        public Criteria andYesterdayNotBetween(Integer value1, Integer value2) {
            addCriterion("yesterday not between", value1, value2, "yesterday");
            return (Criteria) this;
        }

        public Criteria andTodayIsNull() {
            addCriterion("today is null");
            return (Criteria) this;
        }

        public Criteria andTodayIsNotNull() {
            addCriterion("today is not null");
            return (Criteria) this;
        }

        public Criteria andTodayEqualTo(Integer value) {
            addCriterion("today =", value, "today");
            return (Criteria) this;
        }

        public Criteria andTodayNotEqualTo(Integer value) {
            addCriterion("today <>", value, "today");
            return (Criteria) this;
        }

        public Criteria andTodayGreaterThan(Integer value) {
            addCriterion("today >", value, "today");
            return (Criteria) this;
        }

        public Criteria andTodayGreaterThanOrEqualTo(Integer value) {
            addCriterion("today >=", value, "today");
            return (Criteria) this;
        }

        public Criteria andTodayLessThan(Integer value) {
            addCriterion("today <", value, "today");
            return (Criteria) this;
        }

        public Criteria andTodayLessThanOrEqualTo(Integer value) {
            addCriterion("today <=", value, "today");
            return (Criteria) this;
        }

        public Criteria andTodayIn(List<Integer> values) {
            addCriterion("today in", values, "today");
            return (Criteria) this;
        }

        public Criteria andTodayNotIn(List<Integer> values) {
            addCriterion("today not in", values, "today");
            return (Criteria) this;
        }

        public Criteria andTodayBetween(Integer value1, Integer value2) {
            addCriterion("today between", value1, value2, "today");
            return (Criteria) this;
        }

        public Criteria andTodayNotBetween(Integer value1, Integer value2) {
            addCriterion("today not between", value1, value2, "today");
            return (Criteria) this;
        }

        public Criteria andWeekIsNull() {
            addCriterion("week is null");
            return (Criteria) this;
        }

        public Criteria andWeekIsNotNull() {
            addCriterion("week is not null");
            return (Criteria) this;
        }

        public Criteria andWeekEqualTo(Integer value) {
            addCriterion("week =", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotEqualTo(Integer value) {
            addCriterion("week <>", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThan(Integer value) {
            addCriterion("week >", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThanOrEqualTo(Integer value) {
            addCriterion("week >=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThan(Integer value) {
            addCriterion("week <", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThanOrEqualTo(Integer value) {
            addCriterion("week <=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekIn(List<Integer> values) {
            addCriterion("week in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotIn(List<Integer> values) {
            addCriterion("week not in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekBetween(Integer value1, Integer value2) {
            addCriterion("week between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotBetween(Integer value1, Integer value2) {
            addCriterion("week not between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("month is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("month is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(Integer value) {
            addCriterion("month =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(Integer value) {
            addCriterion("month <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(Integer value) {
            addCriterion("month >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("month >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(Integer value) {
            addCriterion("month <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(Integer value) {
            addCriterion("month <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<Integer> values) {
            addCriterion("month in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<Integer> values) {
            addCriterion("month not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(Integer value1, Integer value2) {
            addCriterion("month between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("month not between", value1, value2, "month");
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