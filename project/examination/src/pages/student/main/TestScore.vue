<template>
    <el-table :data="tableData" height="500" border style="width: 100%">
        <el-table-column prop="courseName" label="课程名称" width="420">
        </el-table-column>
        <el-table-column prop="testType" label="考试名称" width="420">
        </el-table-column>
        <el-table-column prop="grade" label="成绩" width="420">
        </el-table-column>
    </el-table>
</template>

<script>
export default {    
    data () {
        return {
            tableData: [{
                courseName: '这个是本地数据',
                testType: '这个是本地数据',
                grade:'这个是本地数据'
            }]
        }
    },
    methods: {
        update () {
            this.$axios.post(this.apiurl + "/student/selectMyGrade", {
                "uId":this.$store.getters.uId
            })
            .then((result) => {
                var json = result.data;
                console.log(result);
                this.tableData = json
            }).catch((err) => {
                // alert('错误')
                console.log(err);
            });
        }
    },
    mounted() {
        this.update()
    },
}
</script>

<style>

</style>