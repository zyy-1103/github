Vue.createApp({
    methods:{
        transferTo(s){
            location.href=location.href+"/"+s;
        }
    },
}).mount("#header")

Vue.createApp({
    data(){
        return{
            s:{}
        }
    },
    created(){
        axios({
            url:location.href,
            method:'post'
        }).then(res=>{
            this.s=res.data.data;
        })
    }
}).mount("#msg")