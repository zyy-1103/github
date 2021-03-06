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
            showList:[],
            sql1:'',
            sql2:'',
            isOver:1
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
                    this.sql1=res.data.sql1;
                    this.sql2=res.data.sql2;
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
                this.sql1=res.data.sql1;
                this.sql2=res.data.sql2;
            })
        },
        download(){
            this.isOver=0;
            if (!this.sql1 || !this.sql2) {
                alert("请先查询！");
                return;
            }
            axios({
                url: "download",
                method:"post",
                responseType: 'blob',
                data:{
                    sql1:this.sql1,
                    sql2:this.sql2
                }
            }).then(res=>{
                let data=res.data
                let url = window.URL.createObjectURL(new Blob([data]))
                let a = document.createElement('a')
                a.style.display = 'none'
                a.href = url
                a.setAttribute('download','result.xls')
                document.body.appendChild(a)
                a.click() //执行下载
                window.URL.revokeObjectURL(a.href) //释放url
                document.body.removeChild(a) //释放标签
                this.isOver=1;
            });
        }
    },
    created(){
        axios({
            url: "/search/t",
        }).then(res=>{
            if (res.data != "1") {
                alert(res.data);
                history.back();
            }
        })
    },
    watch:{
        isOver(){

        }
    }
}).mount("#all")