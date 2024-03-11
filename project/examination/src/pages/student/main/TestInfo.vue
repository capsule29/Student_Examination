<template>
    <el-table :data="tableData" height="500" border style="width: 100%">
        <el-table-column prop="courseName" label="课程名称" width="180">
        </el-table-column>
        <el-table-column prop="testName" label="考试名称" width="180">
        </el-table-column>
        <el-table-column prop="examDate" label="开始时间">
        </el-table-column>
        <el-table-column prop="testType" label="考试类型">
        </el-table-column>
        <el-table-column prop="limitTime" label="考试时间（分钟）">
        </el-table-column>
        <el-table-column prop="lastTime" label="操作">
            <template slot-scope="scope">
                <el-button size="mini" @click="startTest(scope.row)">进入考试</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script>
export default {
    data () {
        return {
            tableData: [{
                paperId: 1,  //试卷编号
                courseName: "这个是本地数据",
                testName: "这个是本地数据",   //考试名称                  
                examDate: "这个是本地数据", //开始时间
                testType: "这个是本地数据",
                limitTime: "这个是本地数据"        //考试持续时间（分钟）
            }]
        }
    },
    methods: {
        startTest:async function(row) {
            //输入考试密码
            const { value: formValues } = await this.$swal.fire({
                title: '请输入考试密码',
                html:
                    '<input id="swal-input1" class="swal2-input">',
                focusConfirm: false,
                preConfirm: () => {
                    return [
                        document.getElementById('swal-input1').value
                    ]
                }
            })
            //学生进入考试页面(开始考试)	
            if (formValues[0] != '') {
                this.$axios.post(this.apiurl + "/student/startTest",{
                    "paperId": row.paperId,
                    "password": formValues[0]
                }).then((res) => {
                    // 保存试卷信息
                    var json = res.data
                    if(json != []) {
                        this.$store.dispatch('paperId', json.paperId)
                        this.$store.dispatch('limitTime', json.limitTime * 60)
                        this.$store.dispatch('radioList', json.radioList)
                        this.$store.dispatch('multSelectList', json.multSelectList)
                        this.$store.dispatch('judgeList', json.judgeList)
                        this.$router.push('/test')
                    }
                    else {
                        this.$swal('考试密码错误!')
                    }
                }).catch((err) => {
                    console.log(err);
                });
            }
        },
        update () {
            //查询所有待考考试信息
            this.$axios.post(this.apiurl + "/student/serachAllTest", {
                "uId": this.$store.getters.uId
            }).then((result) => {
                this.tableData = result.data
            }).catch((err) => {
                // alert('error')
                console.log(err)
            });
        },
    },
    mounted() {
        this.update()
    },
}
</script>