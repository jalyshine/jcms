package cn.jaly.member.entity;

import java.util.ArrayList;
import java.util.List;

public class MemberGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberGroupExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIsCoreIsNull() {
            addCriterion("is_core is null");
            return (Criteria) this;
        }

        public Criteria andIsCoreIsNotNull() {
            addCriterion("is_core is not null");
            return (Criteria) this;
        }

        public Criteria andIsCoreEqualTo(Boolean value) {
            addCriterion("is_core =", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreNotEqualTo(Boolean value) {
            addCriterion("is_core <>", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreGreaterThan(Boolean value) {
            addCriterion("is_core >", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_core >=", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreLessThan(Boolean value) {
            addCriterion("is_core <", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreLessThanOrEqualTo(Boolean value) {
            addCriterion("is_core <=", value, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreIn(List<Boolean> values) {
            addCriterion("is_core in", values, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreNotIn(List<Boolean> values) {
            addCriterion("is_core not in", values, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreBetween(Boolean value1, Boolean value2) {
            addCriterion("is_core between", value1, value2, "isCore");
            return (Criteria) this;
        }

        public Criteria andIsCoreNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_core not between", value1, value2, "isCore");
            return (Criteria) this;
        }

        public Criteria andStarNumIsNull() {
            addCriterion("star_num is null");
            return (Criteria) this;
        }

        public Criteria andStarNumIsNotNull() {
            addCriterion("star_num is not null");
            return (Criteria) this;
        }

        public Criteria andStarNumEqualTo(Byte value) {
            addCriterion("star_num =", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumNotEqualTo(Byte value) {
            addCriterion("star_num <>", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumGreaterThan(Byte value) {
            addCriterion("star_num >", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumGreaterThanOrEqualTo(Byte value) {
            addCriterion("star_num >=", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumLessThan(Byte value) {
            addCriterion("star_num <", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumLessThanOrEqualTo(Byte value) {
            addCriterion("star_num <=", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumIn(List<Byte> values) {
            addCriterion("star_num in", values, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumNotIn(List<Byte> values) {
            addCriterion("star_num not in", values, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumBetween(Byte value1, Byte value2) {
            addCriterion("star_num between", value1, value2, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumNotBetween(Byte value1, Byte value2) {
            addCriterion("star_num not between", value1, value2, "starNum");
            return (Criteria) this;
        }

        public Criteria andMaxPointIsNull() {
            addCriterion("max_point is null");
            return (Criteria) this;
        }

        public Criteria andMaxPointIsNotNull() {
            addCriterion("max_point is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPointEqualTo(Integer value) {
            addCriterion("max_point =", value, "maxPoint");
            return (Criteria) this;
        }

        public Criteria andMaxPointNotEqualTo(Integer value) {
            addCriterion("max_point <>", value, "maxPoint");
            return (Criteria) this;
        }

        public Criteria andMaxPointGreaterThan(Integer value) {
            addCriterion("max_point >", value, "maxPoint");
            return (Criteria) this;
        }

        public Criteria andMaxPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_point >=", value, "maxPoint");
            return (Criteria) this;
        }

        public Criteria andMaxPointLessThan(Integer value) {
            addCriterion("max_point <", value, "maxPoint");
            return (Criteria) this;
        }

        public Criteria andMaxPointLessThanOrEqualTo(Integer value) {
            addCriterion("max_point <=", value, "maxPoint");
            return (Criteria) this;
        }

        public Criteria andMaxPointIn(List<Integer> values) {
            addCriterion("max_point in", values, "maxPoint");
            return (Criteria) this;
        }

        public Criteria andMaxPointNotIn(List<Integer> values) {
            addCriterion("max_point not in", values, "maxPoint");
            return (Criteria) this;
        }

        public Criteria andMaxPointBetween(Integer value1, Integer value2) {
            addCriterion("max_point between", value1, value2, "maxPoint");
            return (Criteria) this;
        }

        public Criteria andMaxPointNotBetween(Integer value1, Integer value2) {
            addCriterion("max_point not between", value1, value2, "maxPoint");
            return (Criteria) this;
        }

        public Criteria andMemberCountIsNull() {
            addCriterion("member_count is null");
            return (Criteria) this;
        }

        public Criteria andMemberCountIsNotNull() {
            addCriterion("member_count is not null");
            return (Criteria) this;
        }

        public Criteria andMemberCountEqualTo(Integer value) {
            addCriterion("member_count =", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountNotEqualTo(Integer value) {
            addCriterion("member_count <>", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountGreaterThan(Integer value) {
            addCriterion("member_count >", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_count >=", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountLessThan(Integer value) {
            addCriterion("member_count <", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountLessThanOrEqualTo(Integer value) {
            addCriterion("member_count <=", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountIn(List<Integer> values) {
            addCriterion("member_count in", values, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountNotIn(List<Integer> values) {
            addCriterion("member_count not in", values, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountBetween(Integer value1, Integer value2) {
            addCriterion("member_count between", value1, value2, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountNotBetween(Integer value1, Integer value2) {
            addCriterion("member_count not between", value1, value2, "memberCount");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageIsNull() {
            addCriterion("allow_send_message is null");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageIsNotNull() {
            addCriterion("allow_send_message is not null");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageEqualTo(Boolean value) {
            addCriterion("allow_send_message =", value, "allowSendMessage");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageNotEqualTo(Boolean value) {
            addCriterion("allow_send_message <>", value, "allowSendMessage");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageGreaterThan(Boolean value) {
            addCriterion("allow_send_message >", value, "allowSendMessage");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_send_message >=", value, "allowSendMessage");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageLessThan(Boolean value) {
            addCriterion("allow_send_message <", value, "allowSendMessage");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_send_message <=", value, "allowSendMessage");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageIn(List<Boolean> values) {
            addCriterion("allow_send_message in", values, "allowSendMessage");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageNotIn(List<Boolean> values) {
            addCriterion("allow_send_message not in", values, "allowSendMessage");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_send_message between", value1, value2, "allowSendMessage");
            return (Criteria) this;
        }

        public Criteria andAllowSendMessageNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_send_message not between", value1, value2, "allowSendMessage");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumIsNull() {
            addCriterion("allow_message_num is null");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumIsNotNull() {
            addCriterion("allow_message_num is not null");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumEqualTo(Integer value) {
            addCriterion("allow_message_num =", value, "allowMessageNum");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumNotEqualTo(Integer value) {
            addCriterion("allow_message_num <>", value, "allowMessageNum");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumGreaterThan(Integer value) {
            addCriterion("allow_message_num >", value, "allowMessageNum");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("allow_message_num >=", value, "allowMessageNum");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumLessThan(Integer value) {
            addCriterion("allow_message_num <", value, "allowMessageNum");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumLessThanOrEqualTo(Integer value) {
            addCriterion("allow_message_num <=", value, "allowMessageNum");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumIn(List<Integer> values) {
            addCriterion("allow_message_num in", values, "allowMessageNum");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumNotIn(List<Integer> values) {
            addCriterion("allow_message_num not in", values, "allowMessageNum");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumBetween(Integer value1, Integer value2) {
            addCriterion("allow_message_num between", value1, value2, "allowMessageNum");
            return (Criteria) this;
        }

        public Criteria andAllowMessageNumNotBetween(Integer value1, Integer value2) {
            addCriterion("allow_message_num not between", value1, value2, "allowMessageNum");
            return (Criteria) this;
        }

        public Criteria andAllowVisitIsNull() {
            addCriterion("allow_visit is null");
            return (Criteria) this;
        }

        public Criteria andAllowVisitIsNotNull() {
            addCriterion("allow_visit is not null");
            return (Criteria) this;
        }

        public Criteria andAllowVisitEqualTo(Boolean value) {
            addCriterion("allow_visit =", value, "allowVisit");
            return (Criteria) this;
        }

        public Criteria andAllowVisitNotEqualTo(Boolean value) {
            addCriterion("allow_visit <>", value, "allowVisit");
            return (Criteria) this;
        }

        public Criteria andAllowVisitGreaterThan(Boolean value) {
            addCriterion("allow_visit >", value, "allowVisit");
            return (Criteria) this;
        }

        public Criteria andAllowVisitGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_visit >=", value, "allowVisit");
            return (Criteria) this;
        }

        public Criteria andAllowVisitLessThan(Boolean value) {
            addCriterion("allow_visit <", value, "allowVisit");
            return (Criteria) this;
        }

        public Criteria andAllowVisitLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_visit <=", value, "allowVisit");
            return (Criteria) this;
        }

        public Criteria andAllowVisitIn(List<Boolean> values) {
            addCriterion("allow_visit in", values, "allowVisit");
            return (Criteria) this;
        }

        public Criteria andAllowVisitNotIn(List<Boolean> values) {
            addCriterion("allow_visit not in", values, "allowVisit");
            return (Criteria) this;
        }

        public Criteria andAllowVisitBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_visit between", value1, value2, "allowVisit");
            return (Criteria) this;
        }

        public Criteria andAllowVisitNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_visit not between", value1, value2, "allowVisit");
            return (Criteria) this;
        }

        public Criteria andAllowPostIsNull() {
            addCriterion("allow_post is null");
            return (Criteria) this;
        }

        public Criteria andAllowPostIsNotNull() {
            addCriterion("allow_post is not null");
            return (Criteria) this;
        }

        public Criteria andAllowPostEqualTo(Boolean value) {
            addCriterion("allow_post =", value, "allowPost");
            return (Criteria) this;
        }

        public Criteria andAllowPostNotEqualTo(Boolean value) {
            addCriterion("allow_post <>", value, "allowPost");
            return (Criteria) this;
        }

        public Criteria andAllowPostGreaterThan(Boolean value) {
            addCriterion("allow_post >", value, "allowPost");
            return (Criteria) this;
        }

        public Criteria andAllowPostGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_post >=", value, "allowPost");
            return (Criteria) this;
        }

        public Criteria andAllowPostLessThan(Boolean value) {
            addCriterion("allow_post <", value, "allowPost");
            return (Criteria) this;
        }

        public Criteria andAllowPostLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_post <=", value, "allowPost");
            return (Criteria) this;
        }

        public Criteria andAllowPostIn(List<Boolean> values) {
            addCriterion("allow_post in", values, "allowPost");
            return (Criteria) this;
        }

        public Criteria andAllowPostNotIn(List<Boolean> values) {
            addCriterion("allow_post not in", values, "allowPost");
            return (Criteria) this;
        }

        public Criteria andAllowPostBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_post between", value1, value2, "allowPost");
            return (Criteria) this;
        }

        public Criteria andAllowPostNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_post not between", value1, value2, "allowPost");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyIsNull() {
            addCriterion("allow_post_verify is null");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyIsNotNull() {
            addCriterion("allow_post_verify is not null");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyEqualTo(Boolean value) {
            addCriterion("allow_post_verify =", value, "allowPostVerify");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyNotEqualTo(Boolean value) {
            addCriterion("allow_post_verify <>", value, "allowPostVerify");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyGreaterThan(Boolean value) {
            addCriterion("allow_post_verify >", value, "allowPostVerify");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_post_verify >=", value, "allowPostVerify");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyLessThan(Boolean value) {
            addCriterion("allow_post_verify <", value, "allowPostVerify");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_post_verify <=", value, "allowPostVerify");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyIn(List<Boolean> values) {
            addCriterion("allow_post_verify in", values, "allowPostVerify");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyNotIn(List<Boolean> values) {
            addCriterion("allow_post_verify not in", values, "allowPostVerify");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_post_verify between", value1, value2, "allowPostVerify");
            return (Criteria) this;
        }

        public Criteria andAllowPostVerifyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_post_verify not between", value1, value2, "allowPostVerify");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumIsNull() {
            addCriterion("allow_post_num is null");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumIsNotNull() {
            addCriterion("allow_post_num is not null");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumEqualTo(Integer value) {
            addCriterion("allow_post_num =", value, "allowPostNum");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumNotEqualTo(Integer value) {
            addCriterion("allow_post_num <>", value, "allowPostNum");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumGreaterThan(Integer value) {
            addCriterion("allow_post_num >", value, "allowPostNum");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("allow_post_num >=", value, "allowPostNum");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumLessThan(Integer value) {
            addCriterion("allow_post_num <", value, "allowPostNum");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumLessThanOrEqualTo(Integer value) {
            addCriterion("allow_post_num <=", value, "allowPostNum");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumIn(List<Integer> values) {
            addCriterion("allow_post_num in", values, "allowPostNum");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumNotIn(List<Integer> values) {
            addCriterion("allow_post_num not in", values, "allowPostNum");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumBetween(Integer value1, Integer value2) {
            addCriterion("allow_post_num between", value1, value2, "allowPostNum");
            return (Criteria) this;
        }

        public Criteria andAllowPostNumNotBetween(Integer value1, Integer value2) {
            addCriterion("allow_post_num not between", value1, value2, "allowPostNum");
            return (Criteria) this;
        }

        public Criteria andAllowSearchIsNull() {
            addCriterion("allow_search is null");
            return (Criteria) this;
        }

        public Criteria andAllowSearchIsNotNull() {
            addCriterion("allow_search is not null");
            return (Criteria) this;
        }

        public Criteria andAllowSearchEqualTo(Boolean value) {
            addCriterion("allow_search =", value, "allowSearch");
            return (Criteria) this;
        }

        public Criteria andAllowSearchNotEqualTo(Boolean value) {
            addCriterion("allow_search <>", value, "allowSearch");
            return (Criteria) this;
        }

        public Criteria andAllowSearchGreaterThan(Boolean value) {
            addCriterion("allow_search >", value, "allowSearch");
            return (Criteria) this;
        }

        public Criteria andAllowSearchGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_search >=", value, "allowSearch");
            return (Criteria) this;
        }

        public Criteria andAllowSearchLessThan(Boolean value) {
            addCriterion("allow_search <", value, "allowSearch");
            return (Criteria) this;
        }

        public Criteria andAllowSearchLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_search <=", value, "allowSearch");
            return (Criteria) this;
        }

        public Criteria andAllowSearchIn(List<Boolean> values) {
            addCriterion("allow_search in", values, "allowSearch");
            return (Criteria) this;
        }

        public Criteria andAllowSearchNotIn(List<Boolean> values) {
            addCriterion("allow_search not in", values, "allowSearch");
            return (Criteria) this;
        }

        public Criteria andAllowSearchBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_search between", value1, value2, "allowSearch");
            return (Criteria) this;
        }

        public Criteria andAllowSearchNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_search not between", value1, value2, "allowSearch");
            return (Criteria) this;
        }

        public Criteria andAllowUploadIsNull() {
            addCriterion("allow_upload is null");
            return (Criteria) this;
        }

        public Criteria andAllowUploadIsNotNull() {
            addCriterion("allow_upload is not null");
            return (Criteria) this;
        }

        public Criteria andAllowUploadEqualTo(Boolean value) {
            addCriterion("allow_upload =", value, "allowUpload");
            return (Criteria) this;
        }

        public Criteria andAllowUploadNotEqualTo(Boolean value) {
            addCriterion("allow_upload <>", value, "allowUpload");
            return (Criteria) this;
        }

        public Criteria andAllowUploadGreaterThan(Boolean value) {
            addCriterion("allow_upload >", value, "allowUpload");
            return (Criteria) this;
        }

        public Criteria andAllowUploadGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_upload >=", value, "allowUpload");
            return (Criteria) this;
        }

        public Criteria andAllowUploadLessThan(Boolean value) {
            addCriterion("allow_upload <", value, "allowUpload");
            return (Criteria) this;
        }

        public Criteria andAllowUploadLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_upload <=", value, "allowUpload");
            return (Criteria) this;
        }

        public Criteria andAllowUploadIn(List<Boolean> values) {
            addCriterion("allow_upload in", values, "allowUpload");
            return (Criteria) this;
        }

        public Criteria andAllowUploadNotIn(List<Boolean> values) {
            addCriterion("allow_upload not in", values, "allowUpload");
            return (Criteria) this;
        }

        public Criteria andAllowUploadBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_upload between", value1, value2, "allowUpload");
            return (Criteria) this;
        }

        public Criteria andAllowUploadNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_upload not between", value1, value2, "allowUpload");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeIsNull() {
            addCriterion("allow_upgrade is null");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeIsNotNull() {
            addCriterion("allow_upgrade is not null");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeEqualTo(Boolean value) {
            addCriterion("allow_upgrade =", value, "allowUpgrade");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeNotEqualTo(Boolean value) {
            addCriterion("allow_upgrade <>", value, "allowUpgrade");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeGreaterThan(Boolean value) {
            addCriterion("allow_upgrade >", value, "allowUpgrade");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_upgrade >=", value, "allowUpgrade");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeLessThan(Boolean value) {
            addCriterion("allow_upgrade <", value, "allowUpgrade");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_upgrade <=", value, "allowUpgrade");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeIn(List<Boolean> values) {
            addCriterion("allow_upgrade in", values, "allowUpgrade");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeNotIn(List<Boolean> values) {
            addCriterion("allow_upgrade not in", values, "allowUpgrade");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_upgrade between", value1, value2, "allowUpgrade");
            return (Criteria) this;
        }

        public Criteria andAllowUpgradeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_upgrade not between", value1, value2, "allowUpgrade");
            return (Criteria) this;
        }

        public Criteria andPriceDayIsNull() {
            addCriterion("price_day is null");
            return (Criteria) this;
        }

        public Criteria andPriceDayIsNotNull() {
            addCriterion("price_day is not null");
            return (Criteria) this;
        }

        public Criteria andPriceDayEqualTo(Double value) {
            addCriterion("price_day =", value, "priceDay");
            return (Criteria) this;
        }

        public Criteria andPriceDayNotEqualTo(Double value) {
            addCriterion("price_day <>", value, "priceDay");
            return (Criteria) this;
        }

        public Criteria andPriceDayGreaterThan(Double value) {
            addCriterion("price_day >", value, "priceDay");
            return (Criteria) this;
        }

        public Criteria andPriceDayGreaterThanOrEqualTo(Double value) {
            addCriterion("price_day >=", value, "priceDay");
            return (Criteria) this;
        }

        public Criteria andPriceDayLessThan(Double value) {
            addCriterion("price_day <", value, "priceDay");
            return (Criteria) this;
        }

        public Criteria andPriceDayLessThanOrEqualTo(Double value) {
            addCriterion("price_day <=", value, "priceDay");
            return (Criteria) this;
        }

        public Criteria andPriceDayIn(List<Double> values) {
            addCriterion("price_day in", values, "priceDay");
            return (Criteria) this;
        }

        public Criteria andPriceDayNotIn(List<Double> values) {
            addCriterion("price_day not in", values, "priceDay");
            return (Criteria) this;
        }

        public Criteria andPriceDayBetween(Double value1, Double value2) {
            addCriterion("price_day between", value1, value2, "priceDay");
            return (Criteria) this;
        }

        public Criteria andPriceDayNotBetween(Double value1, Double value2) {
            addCriterion("price_day not between", value1, value2, "priceDay");
            return (Criteria) this;
        }

        public Criteria andPriceMonthIsNull() {
            addCriterion("price_month is null");
            return (Criteria) this;
        }

        public Criteria andPriceMonthIsNotNull() {
            addCriterion("price_month is not null");
            return (Criteria) this;
        }

        public Criteria andPriceMonthEqualTo(Double value) {
            addCriterion("price_month =", value, "priceMonth");
            return (Criteria) this;
        }

        public Criteria andPriceMonthNotEqualTo(Double value) {
            addCriterion("price_month <>", value, "priceMonth");
            return (Criteria) this;
        }

        public Criteria andPriceMonthGreaterThan(Double value) {
            addCriterion("price_month >", value, "priceMonth");
            return (Criteria) this;
        }

        public Criteria andPriceMonthGreaterThanOrEqualTo(Double value) {
            addCriterion("price_month >=", value, "priceMonth");
            return (Criteria) this;
        }

        public Criteria andPriceMonthLessThan(Double value) {
            addCriterion("price_month <", value, "priceMonth");
            return (Criteria) this;
        }

        public Criteria andPriceMonthLessThanOrEqualTo(Double value) {
            addCriterion("price_month <=", value, "priceMonth");
            return (Criteria) this;
        }

        public Criteria andPriceMonthIn(List<Double> values) {
            addCriterion("price_month in", values, "priceMonth");
            return (Criteria) this;
        }

        public Criteria andPriceMonthNotIn(List<Double> values) {
            addCriterion("price_month not in", values, "priceMonth");
            return (Criteria) this;
        }

        public Criteria andPriceMonthBetween(Double value1, Double value2) {
            addCriterion("price_month between", value1, value2, "priceMonth");
            return (Criteria) this;
        }

        public Criteria andPriceMonthNotBetween(Double value1, Double value2) {
            addCriterion("price_month not between", value1, value2, "priceMonth");
            return (Criteria) this;
        }

        public Criteria andPriceYearIsNull() {
            addCriterion("price_year is null");
            return (Criteria) this;
        }

        public Criteria andPriceYearIsNotNull() {
            addCriterion("price_year is not null");
            return (Criteria) this;
        }

        public Criteria andPriceYearEqualTo(Double value) {
            addCriterion("price_year =", value, "priceYear");
            return (Criteria) this;
        }

        public Criteria andPriceYearNotEqualTo(Double value) {
            addCriterion("price_year <>", value, "priceYear");
            return (Criteria) this;
        }

        public Criteria andPriceYearGreaterThan(Double value) {
            addCriterion("price_year >", value, "priceYear");
            return (Criteria) this;
        }

        public Criteria andPriceYearGreaterThanOrEqualTo(Double value) {
            addCriterion("price_year >=", value, "priceYear");
            return (Criteria) this;
        }

        public Criteria andPriceYearLessThan(Double value) {
            addCriterion("price_year <", value, "priceYear");
            return (Criteria) this;
        }

        public Criteria andPriceYearLessThanOrEqualTo(Double value) {
            addCriterion("price_year <=", value, "priceYear");
            return (Criteria) this;
        }

        public Criteria andPriceYearIn(List<Double> values) {
            addCriterion("price_year in", values, "priceYear");
            return (Criteria) this;
        }

        public Criteria andPriceYearNotIn(List<Double> values) {
            addCriterion("price_year not in", values, "priceYear");
            return (Criteria) this;
        }

        public Criteria andPriceYearBetween(Double value1, Double value2) {
            addCriterion("price_year between", value1, value2, "priceYear");
            return (Criteria) this;
        }

        public Criteria andPriceYearNotBetween(Double value1, Double value2) {
            addCriterion("price_year not between", value1, value2, "priceYear");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("icon not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andUserNameColorIsNull() {
            addCriterion("user_name_color is null");
            return (Criteria) this;
        }

        public Criteria andUserNameColorIsNotNull() {
            addCriterion("user_name_color is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameColorEqualTo(String value) {
            addCriterion("user_name_color =", value, "userNameColor");
            return (Criteria) this;
        }

        public Criteria andUserNameColorNotEqualTo(String value) {
            addCriterion("user_name_color <>", value, "userNameColor");
            return (Criteria) this;
        }

        public Criteria andUserNameColorGreaterThan(String value) {
            addCriterion("user_name_color >", value, "userNameColor");
            return (Criteria) this;
        }

        public Criteria andUserNameColorGreaterThanOrEqualTo(String value) {
            addCriterion("user_name_color >=", value, "userNameColor");
            return (Criteria) this;
        }

        public Criteria andUserNameColorLessThan(String value) {
            addCriterion("user_name_color <", value, "userNameColor");
            return (Criteria) this;
        }

        public Criteria andUserNameColorLessThanOrEqualTo(String value) {
            addCriterion("user_name_color <=", value, "userNameColor");
            return (Criteria) this;
        }

        public Criteria andUserNameColorLike(String value) {
            addCriterion("user_name_color like", value, "userNameColor");
            return (Criteria) this;
        }

        public Criteria andUserNameColorNotLike(String value) {
            addCriterion("user_name_color not like", value, "userNameColor");
            return (Criteria) this;
        }

        public Criteria andUserNameColorIn(List<String> values) {
            addCriterion("user_name_color in", values, "userNameColor");
            return (Criteria) this;
        }

        public Criteria andUserNameColorNotIn(List<String> values) {
            addCriterion("user_name_color not in", values, "userNameColor");
            return (Criteria) this;
        }

        public Criteria andUserNameColorBetween(String value1, String value2) {
            addCriterion("user_name_color between", value1, value2, "userNameColor");
            return (Criteria) this;
        }

        public Criteria andUserNameColorNotBetween(String value1, String value2) {
            addCriterion("user_name_color not between", value1, value2, "userNameColor");
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

        public Criteria andListOrderIsNull() {
            addCriterion("list_order is null");
            return (Criteria) this;
        }

        public Criteria andListOrderIsNotNull() {
            addCriterion("list_order is not null");
            return (Criteria) this;
        }

        public Criteria andListOrderEqualTo(Integer value) {
            addCriterion("list_order =", value, "listOrder");
            return (Criteria) this;
        }

        public Criteria andListOrderNotEqualTo(Integer value) {
            addCriterion("list_order <>", value, "listOrder");
            return (Criteria) this;
        }

        public Criteria andListOrderGreaterThan(Integer value) {
            addCriterion("list_order >", value, "listOrder");
            return (Criteria) this;
        }

        public Criteria andListOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("list_order >=", value, "listOrder");
            return (Criteria) this;
        }

        public Criteria andListOrderLessThan(Integer value) {
            addCriterion("list_order <", value, "listOrder");
            return (Criteria) this;
        }

        public Criteria andListOrderLessThanOrEqualTo(Integer value) {
            addCriterion("list_order <=", value, "listOrder");
            return (Criteria) this;
        }

        public Criteria andListOrderIn(List<Integer> values) {
            addCriterion("list_order in", values, "listOrder");
            return (Criteria) this;
        }

        public Criteria andListOrderNotIn(List<Integer> values) {
            addCriterion("list_order not in", values, "listOrder");
            return (Criteria) this;
        }

        public Criteria andListOrderBetween(Integer value1, Integer value2) {
            addCriterion("list_order between", value1, value2, "listOrder");
            return (Criteria) this;
        }

        public Criteria andListOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("list_order not between", value1, value2, "listOrder");
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