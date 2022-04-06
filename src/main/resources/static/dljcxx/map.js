window._AMapSecurityConfig = {
    securityJsCode:'密钥',
}

import VueAMap from 'vue-amap'
Vue.use(VueAMap)
VueAMap.initAMapApiLoader({
    // 高德key
    key: '',
    // 插件集合 （插件按需引入）
    // plugin: ['AMap.Geolocation', 'AMap.Autocomplete', 'AMap.PlaceSearch']
    plugin: [
        'AMap.Geocoder',
        'AMap.Autocomplete', // 输入提示插件
        'AMap.PlaceSearch', // POI搜索插件
        'AMap.Scale', // 右下角缩略图插件 比例尺
        'AMap.OverView', // 地图鹰眼插件
        'AMap.ToolBar', // 地图工具条
        'AMap.MapType', // 类别切换控件，实现默认图层与卫星图、实施交通图层之间切换的控制
        'AMap.PolyEditor', // 编辑 折线多，边形
        'AMap.CircleEditor', // 圆形编辑器插件
        'AMap.Geolocation' // 定位控件，用来获取和展示用户主机所在的经纬度位置
    ]
})

export default {
    data: function() {
        let self = this;

        return {
            markers: [],
            searchOption: {
                city: "全国",
                citylimit: false
            },
            zoom: 12,
            loaded: false,
            center: [121.59996, 31.197646],
            plugin: [
                {
                    pName: "Geolocation",
                    // 是否使用高精度定位，默认true
                    enableHighAccuracy: true,
                    // // 超过10秒后停止定位，默认：无穷大
                    // timeout: 10000,
                    // // 自动偏移后的坐标为高德坐标，默认：true
                    convert: true,
                    // // 显示定位按钮，默认：true
                    //showButton: true,
                    // // 定位按钮停靠位置，默认'LB'，左下角
                    // buttonPosition: 'RB',
                    // // 定位成功后在定位到的位置显示标记，默认：true
                    showMarker: true,
                    // 定位成功后用圆圈表示定位精度范围，默认：true
                    showCircle: true,
                    // // 定位成功后将定位到的位置作为地图中心点，默认true
                    panToLocation: true,
                    // // 定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                    zoomToAccuracy: true,

                    events: {
                        init(o) {
                            console.log(1);
                            let addressInfo =
                                JSON.parse(sessionStorage.getItem("addressInfo")) || {};
                            console.log(addressInfo);
                            var placeSearch = new AMap.PlaceSearch();
                            if (addressInfo.name && addressInfo.lng && addressInfo.lat) {
                                console.log("已有位置信息");
                                self.lng = addressInfo.lng;
                                self.lat = addressInfo.lat;
                                self.center = [addressInfo.lng, addressInfo.lat];
                                self.address = addressInfo.address;

                                console.log(addressInfo.name)
                                placeSearch.search(addressInfo.name, function(status, result) {
                                    console.log("定位结果");
                                    console.log(result);
                                    if (result.poiList.pois.length>=1) {
                                        self.addressList = result.poiList.pois;
                                        self.selectedAddress = addressInfo;

                                        console.log("地址列表", self.addressList);
                                        console.log("当前地址", self.selectedAddress);
                                        let isSelectIndex = self.addressList.findIndex(
                                            i => i.id == self.selectedAddress.id
                                        );
                                        console.log("==");
                                        console.log(isSelectIndex);
                                        if (isSelectIndex != -1) {
                                            self.addressList[isSelectIndex].selected = true;
                                        } else {
                                            self.addressList[0].selected = true;
                                        }
                                        self.loaded = true;
                                        self.$nextTick();
                                    }else{
                                        self.loaded = true;
                                    }
                                });
                            } else {
                                // o 是高德地图定位插件实例

                                o.getCurrentPosition((status, result) => {
                                    console.log("无位置信息");
                                    if (result && result.position) {
                                        console.log("获取当前位置信息");
                                        console.log(result);
                                        self.lng = result.position.lng;
                                        self.lat = result.position.lat;
                                        self.center = [self.lng, self.lat];
                                        //定位地址

                                        self.address = result.formattedAddress;
                                        console.log(result, "000");



                                        placeSearch.search(self.address, function(
                                            status,
                                            resultTemp
                                        ) {
                                            console.log(resultTemp)
                                            if (resultTemp.poiList.pois.length>=1) {
                                                console.log("获取定位地址列表");
                                                console.log(resultTemp);
                                                self.addressList = resultTemp.poiList.pois;
                                            }
                                        });

                                        //  为什么要加这一行  没有地址的时候 拿当前定位  但不显示列表
                                        //  列表是要通过 地址名的 formattedAddress
                                        // self.addressList=self.addressList.concat(obj1)

                                        self.loaded = true;
                                        self.$nextTick();
                                    }else{
                                        self.loaded = true;
                                    }
                                });
                            }
                        }
                    }
                }
            ],

            address: "",
            events: {
                click(e) {
                    console.log("=====");
                    console.log(e);
                    let { lng, lat } = e.lnglat;
                    self.lng = lng;
                    self.lat = lat;

                    // 这里通过高德 SDK 完成。
                    var geocoder = new AMap.Geocoder({
                        radius: 1000,
                        extensions: "all"
                    });
                    geocoder.getAddress([lng, lat], function(status, result) {
                        if (status === "complete" && result.info === "OK") {
                            if (result && result.regeocode) {
                                self.address = result.regeocode.formattedAddress;
                                self.name = result.regeocode.addressComponent.city;



                                self.$nextTick();
                            }
                        }
                    });
                }
            },
            lng: 0,
            lat: 0,
            addressList: [],
            selectedAddress: {}
        };
    },
    methods: {
        sendMapMsg(e){
            this.$emit('func',e);
        },
        handleSelectedAddress(e) {
            this.selectedAddress = e;
            console.log(this.selectedAddress);
            let addressInfo = {
                ...e,
                lng: e.location.lng,
                lat: e.location.lat
            };
            sessionStorage.setItem("addressInfo", JSON.stringify(addressInfo));
            //this.$router.go(-1);
        },
        addMarker: function() {
            let lng = 121.5 + Math.round(Math.random() * 1000) / 10000;
            let lat = 31.197646 + Math.round(Math.random() * 500) / 10000;
            this.markers.push([lng, lat]);
        },
        onSearchResult(pois) {
            console.log("搜索结果");
            console.log(pois);
            this.addressList = pois;
            this.address = this.addressList.address;
            let latSum = 0;
            let lngSum = 0;
            if (pois.length > 0) {
                pois.forEach(poi => {
                    let { lng, lat } = poi;
                    lngSum += lng;
                    latSum += lat;
                    this.markers.push([poi.lng, poi.lat]);
                });
                let mapcenter = {
                    lng: lngSum / pois.length,
                    lat: latSum / pois.length
                };
                this.center = [mapcenter.lng, mapcenter.lat];
            }
        }
    },
    watch: {
        address: {
            handler: function(val, oldVal) {
                this.address = val;
                this.$emit("sendAddress", this.address);
            },
            // 深度观察监听
            deep: true
        }
    }
};
