<template>
    <div>
        <!-- 添加 -->
        <el-input v-model="form.courseId" style="width: 180px" placeholder="课程号"></el-input>
        <el-input v-model="form.testName" style="width: 180px" placeholder="考试名称"></el-input>
        <el-input v-model="form.testType" style="width: 180px" placeholder="考试类型"></el-input>
        <el-input v-model="form.classId" style="width: 180px" placeholder="考试班级"></el-input>
        <el-date-picker v-model="form.examDate" style="width: 300px" type="datetime" placeholder="选择考试日期时间"></el-date-picker>
        <el-input v-model.number="form.limitTime" style="width: 180px" placeholder="考试时长（分钟）"></el-input>
        <br>
        <span>单选题数量</span><el-slider v-model="form.radioNumber" show-input></el-slider>
        <span>多选题数量</span><el-slider v-model="form.multSelectNumber" show-input></el-slider>
        <span>判断题数量</span><el-slider v-model="form.judgeNumber" show-input></el-slider>

        <el-input v-model="form.password" style="width: 180px" placeholder="考试密码"></el-input>
        
        <el-button style="text-align:left" @click="add">
            添加试卷
        </el-button>

        <!-- 表格 -->
        <el-table :data="tableData" height="500" border style="width: 100%">
            <el-table-column prop="classId" label="考试班级" width="92">
            </el-table-column>
            <el-table-column prop="courseName" label="考试科目" width="92">
            </el-table-column>
            <el-table-column prop="examDate" label="考试日期" width="95">
            </el-table-column>
            <el-table-column prop="testName" label="考试名称" width="92">
            </el-table-column>
            <el-table-column prop="testType" label="考试类型" width="92">
            </el-table-column>
            <el-table-column prop="radioNumber" label="单选题数量" width="92">
            </el-table-column>
            <el-table-column prop="radioScore" label="单选题分值" width="92">
            </el-table-column>
            <el-table-column prop="multSelectNumber" label="多选题数量" width="92">
            </el-table-column>
            <el-table-column prop="multSelectScore" label="多选题分值" width="92">
            </el-table-column>
            <el-table-column prop="judgeNumber" label="判断题数量" width="92">
            </el-table-column>
            <el-table-column prop="judgeScore" label="判断题分值" width="92">
            </el-table-column>
            <el-table-column prop="limitTime" label="考试时长（分钟）" width="92">
            </el-table-column>
            <el-table-column prop="paperId" label="试卷编号" width="92">
            </el-table-column>
            <el-table-column prop="password" label="考试密码" width="92">
            </el-table-column>
        </el-table>

    </div>
</template>

<script>

export default {
    name: 'PaperView',
    data () {
        return {
            form: {
                courseId: '',
                testName: '',
                testType: '',
                examDate: 0,
                limitTime: '',
                radioNumber: 0,
                multSelectNumber: 0,
                judgeNumber: 0,
                password: '',
                classId: ''
            },
            tableData: [{
                "classId": "0922201",                    //考试班级
                "courseName": "马克思主义基本原理",        //考试科目
                "examDate": "2022-12-09 12:00:00",        //考试日期
                "judgeNumber": 10,                    //判断题数量
                "judgeScore": 1.0,                    //判断题分值
                "limitTime": 90,                        //考试时长
                "multSelectNumber": 10,                //多选题数量
                "multSelectScore": 1.0,                //多选题分值
                "paperId": 1,                            //试卷编号
                "password": "123456",                    //考试密码
                "radioNumber": 10,                        //单选题数量
                "radioScore": 1.0,                        //单选题分值
                "testName": "马克思主义基本原理",            //考试名称
                "testType": "期末"                        //考试类型
            }],
        }
    },
    computed: {
        startTime () {
            return this.form.examDate.getUTCFullYear() + '-' + (this.form.examDate.getUTCMonth() + 1) + '-' + this.form.examDate.getUTCDate()
                + ' ' + this.form.examDate.getUTCHours() + ':' + this.form.examDate.getUTCMinutes() + ':' + this.form.examDate.getUTCSeconds()
        }
    },
    methods: {
        //更新
        update () {
            this.$axios.post(this.apiurl + "/teacher/selectAllPaper", {})
                .then((result) => {
                    this.tableData = result.data
                }).catch((err) => {
                    console.log(err);
                });
        },
        // 添加
        add () {
            // console.log(this.utctobeijing(this.$moment(this.startTime).format()));
            console.log(this.$moment(this.startTime).format('YYYY-MM-DD HH:mm:ss'));
            this.$axios.post(this.apiurl + "/teacher/createPaper", {
                "courseId": this.form.courseId,
                "testName": this.form.testName,
                "testType": this.form.testType,
                "examDate": this.utctobeijing(this.$moment(this.startTime).format()),
                "limitTime": this.form.limitTime,
                "radioNumber": this.form.radioNumber,
                "multSelectNumber": this.form.multSelectNumber,
                "judgeNumber": this.form.judgeNumber,
                "password": this.form.password,
                "classId": this.form.classId
            })
            .then((result) => {
                var json = result.data
                if (json != '' && json.isOK == 'true') {
                    this.$swal({
                        title: '添加成功',
                        icon: 'success'
                    })
                    this.update()
                } else {
                    this.$swal({
                        title: '添加失败',
                        icon: 'error'
                    })
                }
            })
            .catch((err) => {
                console.log(err);
            });
        },
        utctobeijing(utc_datetime) {
            // 转为正常的时间格式 年-月-日 时:分:秒
            let new_datetime = utc_datetime.split("T")[0] + " " + utc_datetime.split("T")[1].split(".")[0];

            // 处理成为时间戳
            let timestamp = new Date(new_datetime.replace(/-/g, '/')).getTime();
            timestamp = timestamp / 1000;
            // 增加8个小时，北京时间比utc时间多八个时区
            timestamp = timestamp + 8 * 60 * 60;

            // 时间戳转为时间
            let date = new Date(parseInt(timestamp) * 1000);
            let YY = date.getFullYear() + '-';
            let MM = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
            let DD = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate());
            let hh = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
            let mm = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
            let ss = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
            return YY + MM + DD + " " + hh + mm + ss;
        }
    },
    mounted () {
        this.update()
    }
}
</script>

<style>

</style>