<template>
    <el-menu default-active="1-4-1" class="el-menu-vertical-demo"
    background-color="#d3dce6"
    active-text-color="#00ff"
    :router="true">
        <el-menu-item index="1" :route="{ path:'/student/searchAllTestInfo'}">
            <i class="el-icon-menu"></i>
            <span slot="title">考试概览</span>
        </el-menu-item>
        <el-menu-item index="2" :route="{ path:'/student/searchAllTestScore'}">
            <i class="el-icon-menu"></i>
            <span slot="title">考试成绩</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
export default {
    name:'DaoHang',
}
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 600px;
}
</style>