<template>
  <div>
    <div class="TestTime">
        考试剩余
        {{ hour }}时
        {{ minuts }}分
        {{ seconds }}秒
    </div>
    <h1>单选题</h1>
    <radio-question
    v-for="(it,idx) in radioList"
    :key="it.question"
    :question="it.question"
    :optionA="it.optionA"
    :optionB="it.optionB"
    :optionC="it.optionC"
    :optionD="it.optionD"
    :idx="idx"
    :questionId="it.radioId"
    ></radio-question>

    <h1>多选题</h1>
    <mult-question
    v-for="(it, idx) in multSelectList"
    :key="it.question"  
    :question="it.question"
    :optionA="it.optionA"
    :optionB="it.optionB"
    :optionC="it.optionC"
    :optionD="it.optionD"
    :optionE="it.optionE"
    :idx="idx"
    :questionId="it.multSelectId"
    ></mult-question>

    <h1>判断题</h1>
    <judge-question v-for="(it, idx) in judgeList"
    :key="it.question"
    :question="it.question"
    :optionA="it.optionA"
    :optionB="it.optionB"
    :idx="idx"
    :questionId="it.judgeId"
    ></judge-question>

    <el-button type="success" @click="submit">交卷</el-button>
    
  </div>
</template>

<script>
import {mapGetters} from 'vuex';
import JudgeQuestion from '../../compoments/JudgeQuestionView';
import MultQuestion from '../../compoments/MultQuestionView';
import RadioQuestion from '../../compoments/RadioQuestionView';


// 防止后退
history.pushState(null, null, document.URL)
window.addEventListener('popstate', function(){
    console.log('禁止页面后退')
    history.pushState(null,null,document.URL)
})
// 防止刷新
window.onbeforeunload = function (e) {
    console.log('禁止页面刷新',e)
    return ''
}


export default {
    name: 'TestView',
    components: { JudgeQuestion, MultQuestion, RadioQuestion },
    data () {
        return {
            timeLimit: 0, // 秒
            timer: '', //定时器ID
            hour: 0,
            minuts: 0,
            seconds: 0,
        }
    },
    watch: {
        timeLimit: function (neww) {
            this.hour = parseInt(neww / 60 / 60)
            this.minuts = parseInt(neww / 60 % 60)
            this.seconds = parseInt(neww % 60)
        }
    },
    computed: {
        ...mapGetters(['radioList','multSelectList','judgeList','radioAns','multSelectAns','judgeAns','paperId','uId']),
    },
    methods: {
        submit () {
            console.log("考试结束");
            this.$axios.post(this.apiurl + "/student/submitTest", {
                "paperId": this.paperId,
                "uId": this.uId,
                "radioList": this.radioAns,
                "multSelectList": this.multSelectAns,
                "judgeList": this.judgeAns
            })
            .then((result) => {
                console.log(result);
                var json = result.data
                this.$swal(`你本次考试的成绩是${json.getScore}`)
                // this.$router.push('/student/searchAllTestScore')
                location.href = "http://192.168.193.126:8080/#/student"
            }).catch((err) => {
                console.log(err);
            });
        },
        //定时器函数
        xxc () {
            this.timeLimit--;
            if (this.timeLimit == 0) {
                console.log("考试结束");
                this.$axios.post(this.apiurl + "/student/submitTest", {
                    "paperId": this.paperId,
                    "uId": this.uid,
                    "radioList": this.radioAns,
                    "multSelectList": this.multiSelectAns,
                    "judgeList": this.judegAns
                })
                .then((result) => {
                    var json = result.data
                    this.$swal(`你本次考试的成绩是${json.getScore}`)
                    location.href = "http://192.168.193.126:8080/#/student"
                }).catch((err) => {
                    console.log(err);
                });
            }
        }
    },
    mounted () {
        this.timeLimit = this.$store.getters.limitTime;
        var _this = this
        this.timer = setInterval(() => {
            _this.xxc()
        }, 1000)
    },
    destroyed() {
        clearTimeout(this.timer)
    },
}
</script>

<style scoped>
div {
        margin: 50px;
    }
    .TestTime{
        font-size: 32px;
        position: fixed;
        top: 70%;
        left: 70%;
        width: auto;
        height: auto;
        background-color: rgb(143, 143, 143);
    }
</style>