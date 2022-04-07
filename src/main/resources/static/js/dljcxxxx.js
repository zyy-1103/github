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
            jd=this.s.jd;
            wd=this.s.wd;
            let map= new AMap.Map('container', {
                center: [this.s.jd, this.s.wd],
                // center: [116.397428, 39.90923],
                layers: [//只显示默认图层的时候，layers可以缺省
                    AMap.createDefaultLayer()//高德默认标准图层
                ],
                zoom: 18
            });
            map.on('click', function(e) {
                document.getElementById("lnglat").value = e.lnglat.getLng() + ',' + e.lnglat.getLat()
            });
            var path = [
                [this.s.jd,this.s.wd],
                [this.s.jde, this.s.wde],
            ];

            var polyline = new AMap.Polyline({
                path: path,
                isOutline: true,
                outlineColor: '#ffeeff',
                borderWeight: 3,
                strokeColor: '#ff8080',
                strokeOpacity: 1,
                strokeWeight: 6,
                // 折线样式还支持 'dashed'
                strokeStyle: "solid",
                // strokeStyle是dashed时有效
                strokeDasharray: [10, 5],
                lineJoin: 'round',
                lineCap: 'round',
                zIndex: 50,
            })
            map.add(polyline);
            map.setFitView();
        })
    }
}).mount("#msg")