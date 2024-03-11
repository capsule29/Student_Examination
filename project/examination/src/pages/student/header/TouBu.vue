<template>
    <div>
        <!-- <h4>
            你好{{ this.$store.getters.name }}
        </h4> -->
            <el-button
            type="danger"
            icon="el-icon-switch-button"
            style="float:right;min-height: 100;"
            @click="goback()">
                退出登录
            </el-button>
    </div>
</template>

<script>
export default {
    methods: {
        goback: function () {
            console.log(this.$store.getters.uId);
            this.$axios.post(this.apiurl + "/student/logout", {
                "uId": this.$store.getters.uId
            }).then((result) => {
                if (result.data.isOK == 'true') {
                    this.$swal('退出成功')
                    this.$router.replace("/")
                }
                else 
                    this.$swal('退出失败')
            }).catch((err) => {
                console.log(err);
            });
            
        },
    },
}
</script>

<style>

</style>