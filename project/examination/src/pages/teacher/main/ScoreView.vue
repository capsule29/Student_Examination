<template>
    <el-table :data="tableData" height="600" border style="width: 100%">
        <el-table-column prop="classId" label="班级">
        </el-table-column>
        <el-table-column prop="utId" label="学号">
        </el-table-column>
        <el-table-column prop="name" label="姓名">
        </el-table-column>
        <el-table-column prop="courseName" label="科目名称">
        </el-table-column>
        <el-table-column prop="testType" label="考试类型">
        </el-table-column>
        <el-table-column prop="grade" label="成绩">
        </el-table-column>
    </el-table>
</template>

<script>
export default {
    data () {
        return {
            tableData: [{
                        // "uId": "这是本地数据",
                        // "name": "这是本地数据",
                        // "classId": "这是本地数据",
                        // "courseName": "这是本地数据",
                        // "testType": "这是本地数据",
                        // "grade": "这是本地数据",
                    }]
        }
    },
    methods: {
        update () {
            this.$axios.post(this.apiurl + "teacher/selectAllGrade", {})
                .then((result) => {
                this.tableData = result.data
            }).catch((err) => {
                console.log(err);
            });
        }
    },
    mounted() {
        this.update();
    },
}
</script>

<style>

</style>