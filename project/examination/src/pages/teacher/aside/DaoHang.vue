<template>
    <el-menu default-active="1-4-1" class="el-menu-vertical-demo"
    background-color="#d3dce6"
    active-text-color="#00ff"
    :router="true">
        <el-menu-item index="1" :route="{ path:'/teacher/studentGrade'}">
            <i class="el-icon-search"></i>
            <span slot="title">考试成绩</span>
        </el-menu-item>
        <el-submenu index="2">
                <template slot="title">
                    <i class="el-icon-collection"></i>
                    <span>题库管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="1-1" :route="{ path:'/teacher/questionBank1'}"><span>单选</span></el-menu-item>
                    <el-menu-item index="1-2" :route="{ path:'/teacher/questionBank2'}"><span>多选</span></el-menu-item>
                    <el-menu-item index="1-3" :route="{ path:'/teacher/questionBank3'}"><span>判断</span></el-menu-item>
                </el-menu-item-group>
        </el-submenu>

        <el-menu-item index="2" :route="{ path:'/teacher/course'}">
            <i class="el-icon-search"></i>
            <span slot="title">科目信息</span>
        </el-menu-item>
        <el-menu-item index="4" :route="{ path:'/teacher/class'}">
            <i class="el-icon-search"></i>
            <span slot="title">班级信息</span>
        </el-menu-item>
        <el-menu-item index="5" :route="{ path:'/teacher/student'}">
            <i class="el-icon-search"></i>
            <span slot="title">学生信息</span>
        </el-menu-item>
        <el-menu-item index="6" :route="{ path:'/teacher/paper'}">
            <i class="el-icon-collection"></i>
            <span slot="title">考试信息</span>
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