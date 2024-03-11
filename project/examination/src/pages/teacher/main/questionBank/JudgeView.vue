<template>
    <div>
        <!-- 添加题目 -->
        <el-button style="text-align:left" @click="add">
            添加判断题
        </el-button>

        <!-- 表格 -->
        <el-table :data="tableData" height="500" border style="width: 100%">
            <el-table-column prop="judgeId" label="题目ID" width="100">
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
        <el-pagination layout="prev, pager, next" :total="maxPage * 10" @current-change="handleCurrentChange">
        </el-pagination>
    </div>
</template>

<script>
export default {
    name: 'JudgeView',
    data () {
        return {
            tableData: [{
                // judgeId: "这是本地数据3",
                // question: "这是本地数据3",
                // optionA: "这是本地数据3",
                // optionB: "这是本地数据3",
                // answer: "这是本地数据3"
            }],
            maxPage:0
        }
    },
    methods: {
        //题目细节
        detail (row) {
            this.$swal({
                title:'题目选项',
                // <div>A.${row.optionA}</div>
                // <div>B.${row.optionB}</div>
                html: `
                    <div>答案:${row.answer}</div>
                `
            })
        },
        //更新题目
        update () {
            this.$axios.post(this.apiurl + "teacher/selectJudgeByPage", {
                "page": this.$store.getters.page
            }).then((result) => {
                this.tableData = result.data
            }).catch((err) => {
                console.log(err);
            });
        },
        // 更改页码
        handleCurrentChange(page) {
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
                    '<input id="swal-input5" class="swal2-input" placeholder="答案（请复制上面的选项描述）">',
                focusConfirm: false,
                preConfirm: () => {
                    return [
                        document.getElementById('swal-input1').value,
                        document.getElementById('swal-input2').value,
                        document.getElementById('swal-input3').value,
                        document.getElementById('swal-input4').value,
                        document.getElementById('swal-input5').value
                    ]
                }
            })
            if (formValues) {
                this.$axios.post(this.apiurl + "teacher/addJudge", {
                    "courseId": formValues[0],
                    "question": formValues[1],
                    "optionA": formValues[2],
                    "optionB": formValues[3],
                    "answer": formValues[4]
                })
                .then((result) => {
                    var json = result.data
                    if (json != '' && json.isOK == 'true'){
                        this.$swal({
                            title: '添加成功',
                            icon:'success'
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
            this.$axios.post(this.apiurl + "/judge/getMaxPage", {})
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