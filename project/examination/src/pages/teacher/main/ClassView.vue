<template>
    <div>
        <!-- 添加 -->
        <el-button style="text-align:left" @click="add">
            添加班级
        </el-button>

        <!-- 表格 -->
        <el-table :data="tableData" height="500" border style="width: 100%">
            <el-table-column prop="classId" label="班级号" width="100">
            </el-table-column>
            <el-table-column prop="className" label="班级名称" width="1000">
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
                "classId": "0922201",    //班级号
                "className": "软单1"        //班级名称
            }],
        }
    },
    methods: {
        //更新
        update () {
            this.$axios.post(this.apiurl + "/teacher/selectAllClass", {})
            .then((result) => {
                this.tableData = result.data
            }).catch((err) => {
                console.log(err);
            });
        },
        // 添加
        add: async function () {
            const { value: formValues } = await this.$swal.fire({
                title: '添加班级',
                html:
                    '<input id="swal-input1" class="swal2-input" placeholder="班级号">' +
                    '<input id="swal-input2" class="swal2-input" placeholder="班级名称">',
                focusConfirm: false,
                preConfirm: () => {
                    return [
                        document.getElementById('swal-input1').value,
                        document.getElementById('swal-input2').value,
                    ]
                }
            })
            if (formValues) {
                this.$axios.post(this.apiurl + "/teacher/addClass", {
                    "classId": formValues[0],
                    "className": formValues[1],
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