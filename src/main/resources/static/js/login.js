const vm=Vue.createApp({
    data(){
        return{
            dlm:'',
            mm:'',
            errorMsg:''
        }
    },
    methods:{
        login(){
            axios({
                url: "http://localhost/login",
                method:"post",
                data:{
                    dlm:this.dlm,
                    mm:this.mm
                }
            }).then((res)=>{
                if(res.data=="1"){
                    location.href="/"
                }else{
                    this.errorMsg=res.data;
                }
            }).catch((e)=>{
                this.errorMsg = "抱歉，由于服务器内部原因导致异常，请刷新重试";
            })
        }
    }
}).mount("#sign")