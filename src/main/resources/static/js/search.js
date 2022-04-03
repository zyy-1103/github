Vue.createApp({
    data(){
        return{
            la : [{a:"bzp",b:"标志牌"},{a:"dlq",b:"导流区"},{a:"gjzd",b:"公交站点"},{a:"glss",b:"隔离设施"},{a:"isglss",b:"安全锥"},{a:"jshcd",b:"减速缓冲带"},{a:"jsz",b:"警示柱"},{a:"jzwcrk",b:"建筑物出入口"},{a:"lnbw",b:"路内泊位"},{a:"lsydbz",b:"临时移动标志"},{a:"rxhdx",b:"人行横道线"},{a:"ssglbsd",b:"爆闪灯"},{a:"tqlb",b:"突起路标"},{a:"xhdlk",b:"信号灯路口"}],
            lb: [{}],
            grade:'sszd',
            orgName:'',
            dlInfo:'',
            dlData:'',
            t:[],
            y:[],
            d:[],
            msg:[{}],
            showList:[]
        }
    },
    methods:{
        tb(x){
            let index=0
            for (index = 0; index < this.la.length; index++) {
                if(this.la[index].a==x)
                    break
            }
            if (this.lb[0].a===undefined) {
                this.lb[0]=this.la[index]
            }else{
                this.lb.push(this.la[index]);
            }
            this.la.splice(index, 1);
            console.log(this.lb[0])
        },
        ta(x){
            let index=0
            for (index = 0; index < this.lb.length; index++) {
                if(this.lb[index].a==x)
                    break
            }
            this.la.push(this.lb[index]);
            this.lb.splice(index, 1);
        },
        search(){
            if (this.orgName == '') {
                this.searchAll();
            }else{
                this.searchCondition();
            }
        },
        searchAll(){
            if(this.orgName==''){
                axios({
                    url:"searchAll",
                    method:"post",
                    data:{
                        grade:this.grade
                    }
                }).then(res=>{
                    this.msg = res.data.data;
                    console.log(this.msg)
                })
            }
        },
        searchCondition(){
            let a=this.lb;
            let b=this.showList;
            for (let i = 0; i < a.length; i++) {
                b[i]=a[i].a;
            }
            axios({
                url:"searchCon",
                method:"post",
                data:{
                    grade:this.grade,
                    orgName:this.orgName,
                    dlInfo:this.dlInfo,
                    dlData:this.dlData,
                    showList:b,
                    t:this.t,
                    y:this.y,
                    d:this.d
                }
            }).then(res=>{
                this.msg=res.data.data;
                console.log(this.msg);
            })
        }
    },
    created(){

    }
}).mount("#all")