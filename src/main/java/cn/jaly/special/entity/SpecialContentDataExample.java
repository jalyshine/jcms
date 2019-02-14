package cn.jaly.special.entity;

import java.util.ArrayList;
import java.util.List;

public class SpecialContentDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpecialContentDataExample() {
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

        public Criteria andSpecialContentIdIsNull() {
            addCriterion("special_content_id is null");
            return (Criteria) this;
        }

        public Criteria andSpecialContentIdIsNotNull() {
            addCriterion("special_content_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialContentIdEqualTo(Integer value) {
            addCriterion("special_content_id =", value, "specialContentId");
            return (Criteria) this;
        }

        public Criteria andSpecialContentIdNotEqualTo(Integer value) {
            addCriterion("special_content_id <>", value, "specialContentId");
            return (Criteria) this;
        }

        public Criteria andSpecialContentIdGreaterThan(Integer value) {
            addCriterion("special_content_id >", value, "specialContentId");
            return (Criteria) this;
        }

        public Criteria andSpecialContentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("special_content_id >=", value, "specialContentId");
            return (Criteria) this;
        }

        public Criteria andSpecialContentIdLessThan(Integer value) {
            addCriterion("special_content_id <", value, "specialContentId");
            return (Criteria) this;
        }

        public Criteria andSpecialContentIdLessThanOrEqualTo(Integer value) {
            addCriterion("special_content_id <=", value, "specialContentId");
            return (Criteria) this;
        }

        public Criteria andSpecialContentIdIn(List<Integer> values) {
            addCriterion("special_content_id in", values, "specialContentId");
            return (Criteria) this;
        }

        public Criteria andSpecialContentIdNotIn(List<Integer> values) {
            addCriterion("special_content_id not in", values, "specialContentId");
            return (Criteria) this;
        }

        public Criteria andSpecialContentIdBetween(Integer value1, Integer value2) {
            addCriterion("special_content_id between", value1, value2, "specialContentId");
            return (Criteria) this;
        }

        public Criteria andSpecialContentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("special_content_id not between", value1, value2, "specialContentId");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitIsNull() {
            addCriterion("author_unit is null");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitIsNotNull() {
            addCriterion("author_unit is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitEqualTo(String value) {
            addCriterion("author_unit =", value, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitNotEqualTo(String value) {
            addCriterion("author_unit <>", value, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitGreaterThan(String value) {
            addCriterion("author_unit >", value, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitGreaterThanOrEqualTo(String value) {
            addCriterion("author_unit >=", value, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitLessThan(String value) {
            addCriterion("author_unit <", value, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitLessThanOrEqualTo(String value) {
            addCriterion("author_unit <=", value, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitLike(String value) {
            addCriterion("author_unit like", value, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitNotLike(String value) {
            addCriterion("author_unit not like", value, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitIn(List<String> values) {
            addCriterion("author_unit in", values, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitNotIn(List<String> values) {
            addCriterion("author_unit not in", values, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitBetween(String value1, String value2) {
            addCriterion("author_unit between", value1, value2, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andAuthorUnitNotBetween(String value1, String value2) {
            addCriterion("author_unit not between", value1, value2, "authorUnit");
            return (Criteria) this;
        }

        public Criteria andHitsIsNull() {
            addCriterion("hits is null");
            return (Criteria) this;
        }

        public Criteria andHitsIsNotNull() {
            addCriterion("hits is not null");
            return (Criteria) this;
        }

        public Criteria andHitsEqualTo(Integer value) {
            addCriterion("hits =", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotEqualTo(Integer value) {
            addCriterion("hits <>", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsGreaterThan(Integer value) {
            addCriterion("hits >", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsGreaterThanOrEqualTo(Integer value) {
            addCriterion("hits >=", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsLessThan(Integer value) {
            addCriterion("hits <", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsLessThanOrEqualTo(Integer value) {
            addCriterion("hits <=", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsIn(List<Integer> values) {
            addCriterion("hits in", values, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotIn(List<Integer> values) {
            addCriterion("hits not in", values, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsBetween(Integer value1, Integer value2) {
            addCriterion("hits between", value1, value2, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotBetween(Integer value1, Integer value2) {
            addCriterion("hits not between", value1, value2, "hits");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageIsNull() {
            addCriterion("max_char_per_page is null");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageIsNotNull() {
            addCriterion("max_char_per_page is not null");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageEqualTo(Integer value) {
            addCriterion("max_char_per_page =", value, "maxCharPerPage");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageNotEqualTo(Integer value) {
            addCriterion("max_char_per_page <>", value, "maxCharPerPage");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageGreaterThan(Integer value) {
            addCriterion("max_char_per_page >", value, "maxCharPerPage");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_char_per_page >=", value, "maxCharPerPage");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageLessThan(Integer value) {
            addCriterion("max_char_per_page <", value, "maxCharPerPage");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageLessThanOrEqualTo(Integer value) {
            addCriterion("max_char_per_page <=", value, "maxCharPerPage");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageIn(List<Integer> values) {
            addCriterion("max_char_per_page in", values, "maxCharPerPage");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageNotIn(List<Integer> values) {
            addCriterion("max_char_per_page not in", values, "maxCharPerPage");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageBetween(Integer value1, Integer value2) {
            addCriterion("max_char_per_page between", value1, value2, "maxCharPerPage");
            return (Criteria) this;
        }

        public Criteria andMaxCharPerPageNotBetween(Integer value1, Integer value2) {
            addCriterion("max_char_per_page not between", value1, value2, "maxCharPerPage");
            return (Criteria) this;
        }

        public Criteria andPageTypeIsNull() {
            addCriterion("page_type is null");
            return (Criteria) this;
        }

        public Criteria andPageTypeIsNotNull() {
            addCriterion("page_type is not null");
            return (Criteria) this;
        }

        public Criteria andPageTypeEqualTo(Byte value) {
            addCriterion("page_type =", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotEqualTo(Byte value) {
            addCriterion("page_type <>", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeGreaterThan(Byte value) {
            addCriterion("page_type >", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("page_type >=", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeLessThan(Byte value) {
            addCriterion("page_type <", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeLessThanOrEqualTo(Byte value) {
            addCriterion("page_type <=", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeIn(List<Byte> values) {
            addCriterion("page_type in", values, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotIn(List<Byte> values) {
            addCriterion("page_type not in", values, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeBetween(Byte value1, Byte value2) {
            addCriterion("page_type between", value1, value2, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("page_type not between", value1, value2, "pageType");
            return (Criteria) this;
        }

        public Criteria andTitle1IsNull() {
            addCriterion("title1 is null");
            return (Criteria) this;
        }

        public Criteria andTitle1IsNotNull() {
            addCriterion("title1 is not null");
            return (Criteria) this;
        }

        public Criteria andTitle1EqualTo(String value) {
            addCriterion("title1 =", value, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle1NotEqualTo(String value) {
            addCriterion("title1 <>", value, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle1GreaterThan(String value) {
            addCriterion("title1 >", value, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle1GreaterThanOrEqualTo(String value) {
            addCriterion("title1 >=", value, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle1LessThan(String value) {
            addCriterion("title1 <", value, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle1LessThanOrEqualTo(String value) {
            addCriterion("title1 <=", value, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle1Like(String value) {
            addCriterion("title1 like", value, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle1NotLike(String value) {
            addCriterion("title1 not like", value, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle1In(List<String> values) {
            addCriterion("title1 in", values, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle1NotIn(List<String> values) {
            addCriterion("title1 not in", values, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle1Between(String value1, String value2) {
            addCriterion("title1 between", value1, value2, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle1NotBetween(String value1, String value2) {
            addCriterion("title1 not between", value1, value2, "title1");
            return (Criteria) this;
        }

        public Criteria andTitle2IsNull() {
            addCriterion("title2 is null");
            return (Criteria) this;
        }

        public Criteria andTitle2IsNotNull() {
            addCriterion("title2 is not null");
            return (Criteria) this;
        }

        public Criteria andTitle2EqualTo(String value) {
            addCriterion("title2 =", value, "title2");
            return (Criteria) this;
        }

        public Criteria andTitle2NotEqualTo(String value) {
            addCriterion("title2 <>", value, "title2");
            return (Criteria) this;
        }

        public Criteria andTitle2GreaterThan(String value) {
            addCriterion("title2 >", value, "title2");
            return (Criteria) this;
        }

        public Criteria andTitle2GreaterThanOrEqualTo(String value) {
            addCriterion("title2 >=", value, "title2");
            return (Criteria) this;
        }

        public Criteria andTitle2LessThan(String value) {
            addCriterion("title2 <", value, "title2");
            return (Criteria) this;
        }

        public Criteria andTitle2LessThanOrEqualTo(String value) {
            addCriterion("title2 <=", value, "title2");
            return (Criteria) this;
        }

        public Criteria andTitle2Like(String value) {
            addCriterion("title2 like", value, "title2");
            return (Criteria) this;
        }

        public Criteria andTitle2NotLike(String value) {
            addCriterion("title2 not like", value, "title2");
            return (Criteria) this;
        }

        public Criteria andTitle2In(List<String> values) {
            addCriterion("title2 in", values, "title2");
            return (Criteria) this;
        }

        public Criteria andTitle2NotIn(List<String> values) {
            addCriterion("title2 not in", values, "title2");
            return (Criteria) this;
        }

        public Criteria andTitle2Between(String value1, String value2) {
            addCriterion("title2 between", value1, value2, "title2");
            return (Criteria) this;
        }

        public Criteria andTitle2NotBetween(String value1, String value2) {
            addCriterion("title2 not between", value1, value2, "title2");
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