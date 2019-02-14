package cn.jaly.special.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpecialExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpecialExample() {
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

        public Criteria andBannerIsNull() {
            addCriterion("banner is null");
            return (Criteria) this;
        }

        public Criteria andBannerIsNotNull() {
            addCriterion("banner is not null");
            return (Criteria) this;
        }

        public Criteria andBannerEqualTo(String value) {
            addCriterion("banner =", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerNotEqualTo(String value) {
            addCriterion("banner <>", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerGreaterThan(String value) {
            addCriterion("banner >", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerGreaterThanOrEqualTo(String value) {
            addCriterion("banner >=", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerLessThan(String value) {
            addCriterion("banner <", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerLessThanOrEqualTo(String value) {
            addCriterion("banner <=", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerLike(String value) {
            addCriterion("banner like", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerNotLike(String value) {
            addCriterion("banner not like", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerIn(List<String> values) {
            addCriterion("banner in", values, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerNotIn(List<String> values) {
            addCriterion("banner not in", values, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerBetween(String value1, String value2) {
            addCriterion("banner between", value1, value2, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerNotBetween(String value1, String value2) {
            addCriterion("banner not between", value1, value2, "banner");
            return (Criteria) this;
        }

        public Criteria andThumbIsNull() {
            addCriterion("thumb is null");
            return (Criteria) this;
        }

        public Criteria andThumbIsNotNull() {
            addCriterion("thumb is not null");
            return (Criteria) this;
        }

        public Criteria andThumbEqualTo(String value) {
            addCriterion("thumb =", value, "thumb");
            return (Criteria) this;
        }

        public Criteria andThumbNotEqualTo(String value) {
            addCriterion("thumb <>", value, "thumb");
            return (Criteria) this;
        }

        public Criteria andThumbGreaterThan(String value) {
            addCriterion("thumb >", value, "thumb");
            return (Criteria) this;
        }

        public Criteria andThumbGreaterThanOrEqualTo(String value) {
            addCriterion("thumb >=", value, "thumb");
            return (Criteria) this;
        }

        public Criteria andThumbLessThan(String value) {
            addCriterion("thumb <", value, "thumb");
            return (Criteria) this;
        }

        public Criteria andThumbLessThanOrEqualTo(String value) {
            addCriterion("thumb <=", value, "thumb");
            return (Criteria) this;
        }

        public Criteria andThumbLike(String value) {
            addCriterion("thumb like", value, "thumb");
            return (Criteria) this;
        }

        public Criteria andThumbNotLike(String value) {
            addCriterion("thumb not like", value, "thumb");
            return (Criteria) this;
        }

        public Criteria andThumbIn(List<String> values) {
            addCriterion("thumb in", values, "thumb");
            return (Criteria) this;
        }

        public Criteria andThumbNotIn(List<String> values) {
            addCriterion("thumb not in", values, "thumb");
            return (Criteria) this;
        }

        public Criteria andThumbBetween(String value1, String value2) {
            addCriterion("thumb between", value1, value2, "thumb");
            return (Criteria) this;
        }

        public Criteria andThumbNotBetween(String value1, String value2) {
            addCriterion("thumb not between", value1, value2, "thumb");
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

        public Criteria andEliteIsNull() {
            addCriterion("elite is null");
            return (Criteria) this;
        }

        public Criteria andEliteIsNotNull() {
            addCriterion("elite is not null");
            return (Criteria) this;
        }

        public Criteria andEliteEqualTo(Boolean value) {
            addCriterion("elite =", value, "elite");
            return (Criteria) this;
        }

        public Criteria andEliteNotEqualTo(Boolean value) {
            addCriterion("elite <>", value, "elite");
            return (Criteria) this;
        }

        public Criteria andEliteGreaterThan(Boolean value) {
            addCriterion("elite >", value, "elite");
            return (Criteria) this;
        }

        public Criteria andEliteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("elite >=", value, "elite");
            return (Criteria) this;
        }

        public Criteria andEliteLessThan(Boolean value) {
            addCriterion("elite <", value, "elite");
            return (Criteria) this;
        }

        public Criteria andEliteLessThanOrEqualTo(Boolean value) {
            addCriterion("elite <=", value, "elite");
            return (Criteria) this;
        }

        public Criteria andEliteIn(List<Boolean> values) {
            addCriterion("elite in", values, "elite");
            return (Criteria) this;
        }

        public Criteria andEliteNotIn(List<Boolean> values) {
            addCriterion("elite not in", values, "elite");
            return (Criteria) this;
        }

        public Criteria andEliteBetween(Boolean value1, Boolean value2) {
            addCriterion("elite between", value1, value2, "elite");
            return (Criteria) this;
        }

        public Criteria andEliteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("elite not between", value1, value2, "elite");
            return (Criteria) this;
        }

        public Criteria andDirNameIsNull() {
            addCriterion("dir_name is null");
            return (Criteria) this;
        }

        public Criteria andDirNameIsNotNull() {
            addCriterion("dir_name is not null");
            return (Criteria) this;
        }

        public Criteria andDirNameEqualTo(String value) {
            addCriterion("dir_name =", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameNotEqualTo(String value) {
            addCriterion("dir_name <>", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameGreaterThan(String value) {
            addCriterion("dir_name >", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameGreaterThanOrEqualTo(String value) {
            addCriterion("dir_name >=", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameLessThan(String value) {
            addCriterion("dir_name <", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameLessThanOrEqualTo(String value) {
            addCriterion("dir_name <=", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameLike(String value) {
            addCriterion("dir_name like", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameNotLike(String value) {
            addCriterion("dir_name not like", value, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameIn(List<String> values) {
            addCriterion("dir_name in", values, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameNotIn(List<String> values) {
            addCriterion("dir_name not in", values, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameBetween(String value1, String value2) {
            addCriterion("dir_name between", value1, value2, "dirName");
            return (Criteria) this;
        }

        public Criteria andDirNameNotBetween(String value1, String value2) {
            addCriterion("dir_name not between", value1, value2, "dirName");
            return (Criteria) this;
        }

        public Criteria andUiStyleIsNull() {
            addCriterion("ui_style is null");
            return (Criteria) this;
        }

        public Criteria andUiStyleIsNotNull() {
            addCriterion("ui_style is not null");
            return (Criteria) this;
        }

        public Criteria andUiStyleEqualTo(String value) {
            addCriterion("ui_style =", value, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andUiStyleNotEqualTo(String value) {
            addCriterion("ui_style <>", value, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andUiStyleGreaterThan(String value) {
            addCriterion("ui_style >", value, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andUiStyleGreaterThanOrEqualTo(String value) {
            addCriterion("ui_style >=", value, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andUiStyleLessThan(String value) {
            addCriterion("ui_style <", value, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andUiStyleLessThanOrEqualTo(String value) {
            addCriterion("ui_style <=", value, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andUiStyleLike(String value) {
            addCriterion("ui_style like", value, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andUiStyleNotLike(String value) {
            addCriterion("ui_style not like", value, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andUiStyleIn(List<String> values) {
            addCriterion("ui_style in", values, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andUiStyleNotIn(List<String> values) {
            addCriterion("ui_style not in", values, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andUiStyleBetween(String value1, String value2) {
            addCriterion("ui_style between", value1, value2, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andUiStyleNotBetween(String value1, String value2) {
            addCriterion("ui_style not between", value1, value2, "uiStyle");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateIsNull() {
            addCriterion("home_template is null");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateIsNotNull() {
            addCriterion("home_template is not null");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateEqualTo(String value) {
            addCriterion("home_template =", value, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateNotEqualTo(String value) {
            addCriterion("home_template <>", value, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateGreaterThan(String value) {
            addCriterion("home_template >", value, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("home_template >=", value, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateLessThan(String value) {
            addCriterion("home_template <", value, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateLessThanOrEqualTo(String value) {
            addCriterion("home_template <=", value, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateLike(String value) {
            addCriterion("home_template like", value, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateNotLike(String value) {
            addCriterion("home_template not like", value, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateIn(List<String> values) {
            addCriterion("home_template in", values, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateNotIn(List<String> values) {
            addCriterion("home_template not in", values, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateBetween(String value1, String value2) {
            addCriterion("home_template between", value1, value2, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andHomeTemplateNotBetween(String value1, String value2) {
            addCriterion("home_template not between", value1, value2, "homeTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateIsNull() {
            addCriterion("list_template is null");
            return (Criteria) this;
        }

        public Criteria andListTemplateIsNotNull() {
            addCriterion("list_template is not null");
            return (Criteria) this;
        }

        public Criteria andListTemplateEqualTo(String value) {
            addCriterion("list_template =", value, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateNotEqualTo(String value) {
            addCriterion("list_template <>", value, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateGreaterThan(String value) {
            addCriterion("list_template >", value, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("list_template >=", value, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateLessThan(String value) {
            addCriterion("list_template <", value, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateLessThanOrEqualTo(String value) {
            addCriterion("list_template <=", value, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateLike(String value) {
            addCriterion("list_template like", value, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateNotLike(String value) {
            addCriterion("list_template not like", value, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateIn(List<String> values) {
            addCriterion("list_template in", values, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateNotIn(List<String> values) {
            addCriterion("list_template not in", values, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateBetween(String value1, String value2) {
            addCriterion("list_template between", value1, value2, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andListTemplateNotBetween(String value1, String value2) {
            addCriterion("list_template not between", value1, value2, "listTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateIsNull() {
            addCriterion("show_template is null");
            return (Criteria) this;
        }

        public Criteria andShowTemplateIsNotNull() {
            addCriterion("show_template is not null");
            return (Criteria) this;
        }

        public Criteria andShowTemplateEqualTo(String value) {
            addCriterion("show_template =", value, "showTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateNotEqualTo(String value) {
            addCriterion("show_template <>", value, "showTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateGreaterThan(String value) {
            addCriterion("show_template >", value, "showTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("show_template >=", value, "showTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateLessThan(String value) {
            addCriterion("show_template <", value, "showTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateLessThanOrEqualTo(String value) {
            addCriterion("show_template <=", value, "showTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateLike(String value) {
            addCriterion("show_template like", value, "showTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateNotLike(String value) {
            addCriterion("show_template not like", value, "showTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateIn(List<String> values) {
            addCriterion("show_template in", values, "showTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateNotIn(List<String> values) {
            addCriterion("show_template not in", values, "showTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateBetween(String value1, String value2) {
            addCriterion("show_template between", value1, value2, "showTemplate");
            return (Criteria) this;
        }

        public Criteria andShowTemplateNotBetween(String value1, String value2) {
            addCriterion("show_template not between", value1, value2, "showTemplate");
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

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
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

        public Criteria andAdminIdIsNull() {
            addCriterion("admin_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNotNull() {
            addCriterion("admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminIdEqualTo(Integer value) {
            addCriterion("admin_id =", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotEqualTo(Integer value) {
            addCriterion("admin_id <>", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThan(Integer value) {
            addCriterion("admin_id >", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("admin_id >=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThan(Integer value) {
            addCriterion("admin_id <", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThanOrEqualTo(Integer value) {
            addCriterion("admin_id <=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIn(List<Integer> values) {
            addCriterion("admin_id in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotIn(List<Integer> values) {
            addCriterion("admin_id not in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdBetween(Integer value1, Integer value2) {
            addCriterion("admin_id between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotBetween(Integer value1, Integer value2) {
            addCriterion("admin_id not between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andPictureIdIsNull() {
            addCriterion("picture_id is null");
            return (Criteria) this;
        }

        public Criteria andPictureIdIsNotNull() {
            addCriterion("picture_id is not null");
            return (Criteria) this;
        }

        public Criteria andPictureIdEqualTo(Integer value) {
            addCriterion("picture_id =", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdNotEqualTo(Integer value) {
            addCriterion("picture_id <>", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdGreaterThan(Integer value) {
            addCriterion("picture_id >", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("picture_id >=", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdLessThan(Integer value) {
            addCriterion("picture_id <", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdLessThanOrEqualTo(Integer value) {
            addCriterion("picture_id <=", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdIn(List<Integer> values) {
            addCriterion("picture_id in", values, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdNotIn(List<Integer> values) {
            addCriterion("picture_id not in", values, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdBetween(Integer value1, Integer value2) {
            addCriterion("picture_id between", value1, value2, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdNotBetween(Integer value1, Integer value2) {
            addCriterion("picture_id not between", value1, value2, "pictureId");
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