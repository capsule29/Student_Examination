//引入Vue
import Vue from 'vue'
import App from './App.vue'

//引入store(引入了vuex)
import store from './store'

//VueSweetalert2
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
Vue.use(VueSweetalert2);

//Axios
import Axios from "axios";
Vue.prototype.$axios = Axios;

//ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);


// VueRouter
import VueRouter from "vue-router";
Vue.use(VueRouter);
import router from "./router";

//moment.js
import moment from 'moment'
Vue.prototype.$moment = moment

Vue.config.productionTip = false

Vue.prototype.apiurl = 'http://192.168.193.182:8080/'

new Vue({
    render: h => h(App),
    store,
    router,
}).$mount('#app')
