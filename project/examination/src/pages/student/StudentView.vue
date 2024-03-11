<template>
    <el-container>
        <el-aside width="200px">
            <h3>考试系统学生端</h3>
            <dao-hang></dao-hang>
        </el-aside>
        <el-container>
            <el-header>
                <tou-bu></tou-bu>
            </el-header>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
import DaoHang from './aside/DaoHang'
import TouBu from './header/TouBu'

export default {
    name: 'StudentView',
    components: { DaoHang, TouBu}
}
</script>

<style>
    .el-header,
    .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        /* line-height: 160px; */
        line-height: auto;
    }

    body>.el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>