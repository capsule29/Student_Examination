import Vue from 'vue'
import Vuex from 'vuex';
Vue.use(Vuex);

const state = {
    //类似data
    uid: '',
    name: '',
    limitTime: 0,
    radioList: [],
    multSelectList: [],
    judgeList: [],
    page: 1,
    paperId: 0,

    radioAns: [],
    multSelectAns: [],
    judgeAns: [],
}
const actions = {
    //业务逻辑（方法名小写）
    //下标 + 答案
    radioAns (context, obj) {
        context.commit('RADIOANS', obj)
    },
    multSelectAns (context, obj) {
        context.commit('MULTSELECTANS', obj)
    },
    judgeAns (context, obj) {
        // console.log("@@idx", obj.idx);
        // console.log("@@v", obj.v);
        // console.log("@@questionId", obj.questionId);
        context.commit('JUDGEANS', obj)
    },
    paperId (context, paperId) {
        context.commit('PAPERID', paperId)
    },
    uid (context, uid) {
        context.commit('UID', uid)
    },
    name (context, name) {
        context.commit('NAME', name)
    },
    limitTime (context, limitTime) {
        context.commit('LIMITTIME', limitTime)
    },
    radioList (context, radioList) {
        context.commit('RADIOLIST', radioList)
    },
    multSelectList (context, multSelectList) {
        context.commit('MULTSELECTLIST', multSelectList)
    },
    judgeList (context, judgeList) {
        context.commit('JUDGELIST', judgeList)
    },
    page (context, page) {
        context.commit('PAGE', page)
    },
}
const mutations = {
    //处理（方法名大写）
    RADIOANS (state, obj) {
        state.radioAns[obj.idx] = { "radioId": obj.questionId, "answer": obj.v }
        // console.log(state.radioAns);
    },
    MULTSELECTANS (state, obj) {
        state.multSelectAns[obj.idx] = { "multSelectId": obj.questionId, "answer": obj.v }
        console.log(state.multSelectAns);
    },
    JUDGEANS (state, obj) {
        state.judgeAns[obj.idx] = { "judgeId": obj.questionId, "answer": obj.v }
        // console.log(state.judgeAns);
    },
    PAPERID (state, paperId) {
        state.paperId = paperId
    },
    UID (state, uid) {
        state.uid = uid
    },
    NAME (state, name) {
        state.name = name
    },
    LIMITTIME (state, limitTime) {
        state.limitTime = limitTime
    },
    RADIOLIST (state, v) {
        state.radioList = v
    },
    MULTSELECTLIST (state, v) {
        state.multSelectList = v
    },
    JUDGELIST (state, v) {
        state.judgeList = v
    },
    PAGE (state, v) {
        state.page = v
    },
}
const getters = {
    //类似计算属性
    radioAns (state) {
        return state.radioAns
    },
    multSelectAns (state) {
        return state.multSelectAns
    },
    judgeAns (state) {
        return state.judgeAns
    },
    paperId (state) {
        return state.paperId
    },
    uId (state) {
        return state.uid
    },
    name (state) {
        return state.name
    },
    limitTime (state) {
        return state.limitTime
    },
    radioList (state) {
        return state.radioList
    },
    multSelectList (state) {
        return state.multSelectList
    },
    judgeList (state) {
        return state.judgeList
    },
    page (state) {
        return state.page
    },
}

export default new Vuex.Store({
    actions,
    mutations,
    state,
    getters
})