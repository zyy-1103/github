let tableName = location.href.split("/")[location.href.split("/").length - 2];
alert(tableName);
Vue.createApp({
    data(){
        return{
            s:{}
        }
    },
    created(){
        axios({
            url:location.href,
            method:'post',

        }).then(res=>{
            this.s=res.data.data;
        })
    }
}).mount("#msg")