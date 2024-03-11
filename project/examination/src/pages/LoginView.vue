<template>
    <div class="body">
        <div class="login-box">
            <h4>考试系统登录页面</h4>
            <div class="input-box">
                <div class="input-text">
                    <span class="login-login"></span>
                    <input type="text" placeholder="账号" v-model="account">
                </div>
                <div class="input-text">
                    <span class="login-passwd"></span>
                    <input type="password" placeholder="密码" v-model="password">
                </div>
                <div class="button" @click="login">登录</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name:'LoginView',
    data () {
        return {
            account: "",
            password: "",
        }
    },
    methods: {
        login () {
            //发送登录请求
            this.$axios.post(this.apiurl + "/login",{
                    "account": this.account,
                    "password": this.password
            }).then((res) => {
                var json = res.data
                //学生
                if (json != null && json.permission == "student") {
                    this.$store.dispatch('uid', json.uId)
                    this.$store.dispatch('name', json.name)
                    this.$swal({
                        title: '登陆成功',
                        icon:'success'
                    })
                    this.$router.push('/student')
                }
                else if (json != null && json.permission == "teacher") {
                    this.$store.dispatch('uid', json.uId)
                    this.$store.dispatch('name', json.name)
                    this.$swal({
                        title: '登陆成功',
                        icon: 'success'
                    })
                    this.$router.push('/teacher')
                }
                else {
                    this.$swal({
                        title: '账号密码错误或已登录',
                        icon: 'error'
                    })
                }
            }).catch((err) => {
                // alert('发送了一个错误')
                console.log(err)
            })
        }
    }
}
</script>

<style scoped>
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }
    .body {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        background-color: rgb(82, 157, 255);
        background-repeat: no-repeat;
        background-position: center;
        background-size: cover;
        overflow: hidden;
    }

    .login-box {
        display: flex;
        justify-content: center;
        align-content: space-around;
        flex-wrap: wrap;
        width: 600px;
        height: 400px;
        background-color: rgba(141, 194, 255, 0.4);
        border: 10px;
        padding: 20px 50px;
        border-radius: 20px;
    }

    .login-box h4 {
        width: 100%;
        display: flex;
        justify-content: center;
        color: rgb(255, 255, 255);
        font-size: 30px;
    }

    .login-box .input-box .input-text {
        width: 100%;
        display: flex;
        justify-content: center;
    }

    .login-box .input-box .input-text span {
        color: aliceblue;
        font-size: 18px;
        margin-top: 20px;

    }

    .login-box .input-box .input-text input {
        border: 0;
        padding: 6px;
        border-bottom: 1px solid white;
        background-color: #ffffff00;
        color: rgb(255, 255, 255);
        margin-top: 20px;
    }

    .login-box .input-box .button {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 25px;
        width: 145px;
        height: 25px;
        color: #fff;
        background: linear-gradient(120deg, #a6c0fe 0%, #2E8B57 100%);
        border-radius: 25px;
        cursor: pointer;
    }

    .login-box .input-box .signup {
        width: 100%;
        display: flex;
        justify-content: center;
        margin-top: 40px;
        color: #fff;
        font-size: 15px;

    }

    .login-box .input-box .signup a {
        color: #a6c0fe;
        text-decoration: none;
    }
</style>