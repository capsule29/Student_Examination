<template>
    <div>
        <!-- 添加 -->
        <el-button style="text-align:left" @click="add">
            添加学生
        </el-button>

        <!-- 表格 -->
        <el-table :data="tableData" height="500" border style="width: 100%">
            <el-table-column prop="uId" label="学号" width="100">
            </el-table-column>
            <el-table-column prop="classId" label="班级" width="100">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="1000">
            </el-table-column>
        </el-table>

    </div>
</template>

<script>
export default {
    name: 'TeacherStudent',
    data () {
        return {
            tableData: [{
                uId: "092220158",//学号
                name: "zhangsan",//姓名
                classId: "0922201"//班级
            }],
        }
    },
    methods: {
        //更新
        update () {
            this.$axios.post(this.apiurl + "/teacher/selectAllStudent", {})
            .then((result) => {
                this.tableData = result.data
            }).catch((err) => {
                console.log(err);
            });
        },
        // 添加
        add: async function () {
            const { value: formValues } = await this.$swal.fire({
                title: '添加学生',
                html:
                    '<input id="swal-input1" class="swal2-input" placeholder="学号">' +
                    '<input id="swal-input2" class="swal2-input" placeholder="姓名">' +
                    '<input id="swal-input3" class="swal2-input" placeholder="班级号">',
                focusConfirm: false,
                preConfirm: () => {
                    return [
                        document.getElementById('swal-input1').value,
                        document.getElementById('swal-input2').value,
                        document.getElementById('swal-input3').value,
                    ]
                }
            })
            if (formValues) {
                this.$axios.post(this.apiurl + "/teacher/addStudent", {
                    "uId": formValues[0],
                    "name": formValues[1],
                    "optclassIdionA": formValues[2],
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