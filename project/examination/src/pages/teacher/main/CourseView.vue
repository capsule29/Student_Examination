<template>
    <div>
        <!-- 添加 -->
        <el-button style="text-align:left" @click="add">
            添加科目
        </el-button>

        <!-- 表格 -->
        <el-table :data="tableData" height="500" border style="width: 100%">
            <el-table-column prop="courseId" label="课程ID" width="100">
            </el-table-column>
            <el-table-column prop="courseName" label="课程名" width="1000">
            </el-table-column>
        </el-table>

    </div>
</template>

<script>
export default {
    name: 'ClassView',
    data () {
        return {
            tableData: [{
                courseId: "1",
                courseName: "马克思主义原理"
            }],
        }
    },
    methods: {
        //更新
        update () {
            this.$axios.post(this.apiurl + "/teacher/selectAllCourse", {})
            .then((result) => {
                this.tableData = result.data
            }).catch((err) => {
                console.log(err);
            });
        },
        // 添加
        add: async function () {
            const { value: formValues } = await this.$swal.fire({
                title: '添加科目',
                html:
                    '<input id="swal-input1" class="swal2-input" placeholder="课程名">',
                focusConfirm: false,
                preConfirm: () => {
                    return [
                        document.getElementById('swal-input1').value,
                    ]
                }
            })
            if (formValues) {
                this.$axios.post(this.apiurl + "/teacher/addCourse", {
                    "courseName": formValues[0],
                })
                .then((result) => {
                    var json = result.data
                    if (json != '' && json.isOK == 'true') {
                        this.$swal({
                            title: '添加成功',
                            icon: 'success'
                        })
                    } else {
                        this.$swal({
                            title: '添加失败',
                            icon: 'error'
                        })
                    }
                }).catch((err) => {
                    console.log(err);
                });
            }
        }
    },
    mounted () {
        this.update()
    }
}
</script>

<style>

</style>