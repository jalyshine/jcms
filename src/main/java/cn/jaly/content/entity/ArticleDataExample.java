package cn.jaly.content.entity;

import java.util.ArrayList;
import java.util.List;

public class ArticleDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleDataExample() {
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

        public Criteria andArticleIdIsNull() {
            addCriterion("article_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("article_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Integer value) {
            addCriterion("article_id =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Integer value) {
            addCriterion("article_id <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Integer value) {
            addCriterion("article_id >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_id >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Integer value) {
            addCriterion("article_id <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
            addCriterion("article_id <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Integer> values) {
            addCriterion("article_id in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Integer> values) {
            addCriterion("article_id not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Integer value1, Integer value2) {
            addCriterion("article_id between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("article_id not between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andAllowCommentIsNull() {
            addCriterion("allow_comment is null");
            return (Criteria) this;
        }

        public Criteria andAllowCommentIsNotNull() {
            addCriterion("allow_comment is not null");
            return (Criteria) this;
        }

        public Criteria andAllowCommentEqualTo(Boolean value) {
            addCriterion("allow_comment =", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentNotEqualTo(Boolean value) {
            addCriterion("allow_comment <>", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentGreaterThan(Boolean value) {
            addCriterion("allow_comment >", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_comment >=", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentLessThan(Boolean value) {
            addCriterion("allow_comment <", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_comment <=", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentIn(List<Boolean> values) {
            addCriterion("allow_comment in", values, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentNotIn(List<Boolean> values) {
            addCriterion("allow_comment not in", values, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_comment between", value1, value2, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_comment not between", value1, value2, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsIsNull() {
            addCriterion("allow_groups is null");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsIsNotNull() {
            addCriterion("allow_groups is not null");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsEqualTo(String value) {
            addCriterion("allow_groups =", value, "allowGroups");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsNotEqualTo(String value) {
            addCriterion("allow_groups <>", value, "allowGroups");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsGreaterThan(String value) {
            addCriterion("allow_groups >", value, "allowGroups");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsGreaterThanOrEqualTo(String value) {
            addCriterion("allow_groups >=", value, "allowGroups");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsLessThan(String value) {
            addCriterion("allow_groups <", value, "allowGroups");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsLessThanOrEqualTo(String value) {
            addCriterion("allow_groups <=", value, "allowGroups");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsLike(String value) {
            addCriterion("allow_groups like", value, "allowGroups");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsNotLike(String value) {
            addCriterion("allow_groups not like", value, "allowGroups");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsIn(List<String> values) {
            addCriterion("allow_groups in", values, "allowGroups");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsNotIn(List<String> values) {
            addCriterion("allow_groups not in", values, "allowGroups");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsBetween(String value1, String value2) {
            addCriterion("allow_groups between", value1, value2, "allowGroups");
            return (Criteria) this;
        }

        public Criteria andAllowGroupsNotBetween(String value1, String value2) {
            addCriterion("allow_groups not between", value1, value2, "allowGroups");
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

        public Criteria andReadPointIsNull() {
            addCriterion("read_point is null");
            return (Criteria) this;
        }

        public Criteria andReadPointIsNotNull() {
            addCriterion("read_point is not null");
            return (Criteria) this;
        }

        public Criteria andReadPointEqualTo(Integer value) {
            addCriterion("read_point =", value, "readPoint");
            return (Criteria) this;
        }

        public Criteria andReadPointNotEqualTo(Integer value) {
            addCriterion("read_point <>", value, "readPoint");
            return (Criteria) this;
        }

        public Criteria andReadPointGreaterThan(Integer value) {
            addCriterion("read_point >", value, "readPoint");
            return (Criteria) this;
        }

        public Criteria andReadPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("read_point >=", value, "readPoint");
            return (Criteria) this;
        }

        public Criteria andReadPointLessThan(Integer value) {
            addCriterion("read_point <", value, "readPoint");
            return (Criteria) this;
        }

        public Criteria andReadPointLessThanOrEqualTo(Integer value) {
            addCriterion("read_point <=", value, "readPoint");
            return (Criteria) this;
        }

        public Criteria andReadPointIn(List<Integer> values) {
            addCriterion("read_point in", values, "readPoint");
            return (Criteria) this;
        }

        public Criteria andReadPointNotIn(List<Integer> values) {
            addCriterion("read_point not in", values, "readPoint");
            return (Criteria) this;
        }

        public Criteria andReadPointBetween(Integer value1, Integer value2) {
            addCriterion("read_point between", value1, value2, "readPoint");
            return (Criteria) this;
        }

        public Criteria andReadPointNotBetween(Integer value1, Integer value2) {
            addCriterion("read_point not between", value1, value2, "readPoint");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsIsNull() {
            addCriterion("recommend_positions is null");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsIsNotNull() {
            addCriterion("recommend_positions is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsEqualTo(String value) {
            addCriterion("recommend_positions =", value, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsNotEqualTo(String value) {
            addCriterion("recommend_positions <>", value, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsGreaterThan(String value) {
            addCriterion("recommend_positions >", value, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsGreaterThanOrEqualTo(String value) {
            addCriterion("recommend_positions >=", value, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsLessThan(String value) {
            addCriterion("recommend_positions <", value, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsLessThanOrEqualTo(String value) {
            addCriterion("recommend_positions <=", value, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsLike(String value) {
            addCriterion("recommend_positions like", value, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsNotLike(String value) {
            addCriterion("recommend_positions not like", value, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsIn(List<String> values) {
            addCriterion("recommend_positions in", values, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsNotIn(List<String> values) {
            addCriterion("recommend_positions not in", values, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsBetween(String value1, String value2) {
            addCriterion("recommend_positions between", value1, value2, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andRecommendPositionsNotBetween(String value1, String value2) {
            addCriterion("recommend_positions not between", value1, value2, "recommendPositions");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdIsNull() {
            addCriterion("copy_from_id is null");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdIsNotNull() {
            addCriterion("copy_from_id is not null");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdEqualTo(Integer value) {
            addCriterion("copy_from_id =", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotEqualTo(Integer value) {
            addCriterion("copy_from_id <>", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThan(Integer value) {
            addCriterion("copy_from_id >", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("copy_from_id >=", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThan(Integer value) {
            addCriterion("copy_from_id <", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdLessThanOrEqualTo(Integer value) {
            addCriterion("copy_from_id <=", value, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdIn(List<Integer> values) {
            addCriterion("copy_from_id in", values, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotIn(List<Integer> values) {
            addCriterion("copy_from_id not in", values, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdBetween(Integer value1, Integer value2) {
            addCriterion("copy_from_id between", value1, value2, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andCopyFromIdNotBetween(Integer value1, Integer value2) {
            addCriterion("copy_from_id not between", value1, value2, "copyFromId");
            return (Criteria) this;
        }

        public Criteria andVoteIdIsNull() {
            addCriterion("vote_id is null");
            return (Criteria) this;
        }

        public Criteria andVoteIdIsNotNull() {
            addCriterion("vote_id is not null");
            return (Criteria) this;
        }

        public Criteria andVoteIdEqualTo(Integer value) {
            addCriterion("vote_id =", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotEqualTo(Integer value) {
            addCriterion("vote_id <>", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdGreaterThan(Integer value) {
            addCriterion("vote_id >", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vote_id >=", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdLessThan(Integer value) {
            addCriterion("vote_id <", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdLessThanOrEqualTo(Integer value) {
            addCriterion("vote_id <=", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdIn(List<Integer> values) {
            addCriterion("vote_id in", values, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotIn(List<Integer> values) {
            addCriterion("vote_id not in", values, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdBetween(Integer value1, Integer value2) {
            addCriterion("vote_id between", value1, value2, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vote_id not between", value1, value2, "voteId");
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