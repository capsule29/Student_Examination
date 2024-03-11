import VueRouter from "vue-router";

import LoginView from '@/pages/LoginView'

import StudentView from '@/pages/student/StudentView'
import TestInfo from '@/pages/student/main/TestInfo'
import TestScore from '@/pages/student/main/TestScore'

import TestView from '@/pages/test/TestView'

import TeacherView from '@/pages/teacher/TeacherView'
import ScoreView from '@/pages/teacher/main/ScoreView'
import ClassView from '@/pages/teacher/main/ClassView'
import TeacherStudent from '@/pages/teacher/main/TeacherStudent'
import CourseView from '@/pages/teacher/main/CourseView'
import PaperView from '@/pages/teacher/main/PaperView'

import RadioView from '@/pages/teacher/main/questionBank/RadioView'
import MultView from '@/pages/teacher/main/questionBank/MultView'
import JudgeView from '@/pages/teacher/main/questionBank/JudgeView'


export default new VueRouter({
    routes: [
        {
            // 登录
            path: '/',
            name: 'login',
            component: LoginView,
        }, {
            // 学生
            path: '/student',
            name: 'student',
            redirect: '/student/searchAllTestInfo',
            component: StudentView,
            children: [{
                path: 'searchAllTestInfo',
                component: TestInfo
            }, {
                path: 'searchAllTestScore',
                component: TestScore
            }]
        }, {
            // 考试
            path: '/test',
            name: 'test',
            component: TestView
        }, {
            // 教师 
            path: '/teacher',
            name: 'teacher',
            redirect: '/teacher/studentGrade',
            component: TeacherView,
            children: [{
                path: 'studentGrade',
                component: ScoreView
            }, {
                path: 'questionBank1',
                component: RadioView
            }, {
                path: 'questionBank2',
                component: MultView
            }, {
                path: 'questionBank3',
                component: JudgeView
            }, {
                path: 'class',
                component: ClassView
            }, {
                path: 'student',
                component: TeacherStudent
            }, {
                path: 'course',
                component: CourseView
            }, {
                path: 'paper',
                component: PaperView
            },
            ]
        }
    ]
})