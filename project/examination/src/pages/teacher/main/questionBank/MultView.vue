<template>
    <div>
        <!-- 添加题目 -->
        <el-button style="text-align:left" @click="add">
            添加多选题
        </el-button>

        <!-- 表格 -->
        <el-table :data="tableData" height="500" border style="width: 100%">

            <el-table-column prop="multSelectId" label="题目ID" width="100">
            </el-table-column>

            <el-table-column prop="question" label="题目描述" width="1000">
            </el-table-column>

            <el-table-column prop="lastTime" label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" @click="detail(scope.row)">
                        查看详情
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 页码 -->
        <el-pagination layout="prev, pager, next" :total="maxPage" @current-change="handleCurrentChange">
        </el-pagination>
    </div>
</template>

<script>
export default {
    name: 'MultView',
    data () {
        return {
            tableData: [{
                // multSelectId: "这是本地数据2",
                // question: "这是本地数据2",
                // optionA: "这是本地数据2",
                // optionB: "这是本地数据2",
                // optionC: "这是本地数据2",
                // optionD: "这是本地数据2",
                // optionE: "这是本地数据2",
                // answer: "这是本地数据2"
            }],
            maxPage:0
        }
    },
    methods: {
        //题目细节
        detail (row) {
            this.$swal({
                title: '题目选项',
                html: `
                <div>A.${row.optionA}</div>
                <div>B.${row.optionB}</div>
                <div>C.${row.optionC}</div>
                <div>D.${row.optionD}</div>
                <div>E.${row.optionE}</div>
                <div>答案:${row.answer}</div>
                `
            })
        },
        //更新题目
        update () {
            this.$axios.post(this.apiurl + "teacher/selectMultSelectByPage", {
                "page": this.$store.getters.page
            }).then((result) => {
                this.tableData = result.data
            }).catch((err) => {
                console.log(err);
            });
        },
        // 更改页码
        handleCurrentChange (page) {
            this.$store.dispatch('page', page)
            this.update()
        },
        // 添加题目
        add: async function () {
            const { value: formValues } = await this.$swal.fire({
                title: 'Multiple inputs',
                html:
                    '<input id="swal-input1" class="swal2-input" placeholder="课程号">' +
                    '<input id="swal-input2" class="swal2-input" placeholder="题目描述">' +
                    '<input id="swal-input3" class="swal2-input" placeholder="选项A描述">' +
                    '<input id="swal-input4" class="swal2-input" placeholder="选项B描述">' +
                    '<input id="swal-input5" class="swal2-input" placeholder="选项C描述">' +
                    '<input id="swal-input6" class="swal2-input" placeholder="选项D描述">' +
                    '<input id="swal-input7" class="swal2-input" placeholder="选项E描述">' +
                    '<input id="swal-input8" class="swal2-input" placeholder="答案（请复制上面的选项描述比并用英文逗号分隔）">',
                focusConfirm: false,
                preConfirm: () => {
                    return [
                        document.getElementById('swal-input1').value,
                        document.getElementById('swal-input2').value,
                        document.getElementById('swal-input3').value,
                        document.getElementById('swal-input4').value,
                        document.getElementById('swal-input5').value,
                        document.getElementById('swal-input6').value,
                        document.getElementById('swal-input7').value,
                        document.getElementById('swal-input8').value
                    ]
                }
            })
            if (formValues) {
                this.$axios.post(this.apiurl + "teacher/addMultSelect", {
                    "courseId": formValues[0],
                    "question": formValues[1],
                    "optionA": formValues[2],
                    "optionB": formValues[3],
                    "optionC": formValues[4],
                    "optionD": formValues[5],
                    "optionE": formValues[6],
                    "answer": formValues[7]
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
        },
        page () {
            this.$axios.post(this.apiurl + "/multselect/getMaxPage", {})
                .then((result) => {
                    var json = result.data
                    if (json != '')
                        this.maxPage = json.maxPage
                }).catch((err) => {
                    console.log(err)
                });
        }
    },
    mounted () {
        this.$store.dispatch('page', 1)
        this.update()
        this.page()
    }
}
</script>

<style>

</style>