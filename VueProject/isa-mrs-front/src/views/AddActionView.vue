<template>
    <div>
    <NavbarUser></NavbarUser>
    <div class="signUp">    
        <div class="app">
            <div class="vertical-center" style="margin-top: 85px">
                <div class="inner-block">
                    <div class="signUp">
                        <form @submit.prevent>
                            <h3>Add a new action:</h3>  

                            <div class="form-group required">
                                <table>
                                    <tr>
                                        <td>
                                            <label for="name">Starting date </label>
                                            <input :min="currentDate" v-model="form.startDate" id="date" type="date" class="form-control form-control-lg"/>
                                        </td>
                                        <td>
                                            <label for="surname">Starting time </label>
                                            <input v-model="form.startTime" id="time" type="time" class="form-control form-control-lg"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="form-group required">
                                <table>
                                    <tr>
                                        <td>
                                            <label for="name">Ending date </label>
                                            <input :min="currentDate" v-model="form.endDate" id="date" type="date" class="form-control form-control-lg"/>
                                        </td>
                                        <td>
                                            <label for="surname">Ending time </label>
                                            <input v-model="form.endTime" id="time" type="time" class="form-control form-control-lg"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>

                            <div class="form-group required">
                                <label>Max number of people</label>
                                <input v-model.number="form.maxNumOfPeople" type="number" class="form-control form-control-lg"/>
                            </div>
                            
                            <div class="form-group required">
                                <label>Price </label>
                                <input v-model.number="form.price" type="number" class="form-control form-control-lg">
                            </div>

                            <div class="form-group mb-3">
                                <label>Additional services:</label>
                                <br>
                                <template v-for="tag in this.tags">
                                    <div class="form-group mb-3" :key="tag">
                                        <input type="checkbox" v-model="form.additionalServices" :value="tag"/>
                                        <label :for="tag">{{tag}}</label>
                                    </div>
                                </template>
                            </div>
                            
                            <div class="form-group">
                                <button @click="addAction"  :disabled="isFormValid == false" type="submit" class="btn btn-dark btn-lg btn-block">Add action</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</template>
<script>
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'
    import NavbarUser from '@/components/NavbarUser.vue'

    Vue.use(VueAxios, axios)

    export default {
        name: "AddAction",
        components: {
            NavbarUser,
        },
        data () {
            return {
                form:{
                    startDate: "",
                    startTime: "",
                    endDate: "",
                    endTime: "",
                    maxNumOfPeople: null,
                    price: "",
                    additionalServices: []
                },
                tags: []
            }
        },
        methods: {
            addAction() {
                if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor"){
                    this.sendData("adventures")
                }
                else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner"){
                    this.sendData("ships");
                }
                else if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner"){
                    this.sendData("retreats");
                }
            },
            sendData(service) {
                let dateFrom = new Date(this.form.startDate);
                let dateTo = new Date(this.form.endDate);
                dateFrom.setHours(0, 0, 0, 0);
                dateTo.setHours(0, 0, 0, 0);
                axios.post("http://localhost:8088/" + service + "/add-action/" + this.$route.params.id,
                    {
                        dateFrom: dateFrom,
                        timeFrom: this.form.startTime,
                        dateTo: dateTo,
                        timeTo: this.form.endTime,
                        maxNumOfPeople: this.form.maxNumOfPeople,
                        price: this.form.price,
                        additionalServices: this.form.additionalServices
                    },
                    {
                        headers:{
                            Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                        }
                }).then(() => {
                    alert("Successfully added new action for your service!");
                    router.push('/service-crud');
                }).catch( error => {
                    if (error.response.status === 409) alert("Reservations for this period already exists.");
                    else if (error.response.status === 403) alert("You do not have access for this!");
                    else alert("Could not add service availability");
                })
            },
        },
        computed: {
            priceIsValid() {
                return typeof this.form.price === 'number' && !!this.form.price && this.form.price > 0;
            },
            maxNumOfPeopleIsValid(){
                return typeof this.form.maxNumOfPeople === 'number' && !!this.form.maxNumOfPeople && this.form.maxNumOfPeople > 0;
            },
            datesAreValid() {
                let today = new Date();
                let todayHours = today.getHours();
                let todayMinutes = today.getMinutes();
                if (todayHours < 10) {
                    todayHours = '0' + todayHours;
                }
                if (todayMinutes < 10) {
                    todayMinutes = '0' + todayMinutes;
                }
                let todayTime = todayHours + ':' + todayMinutes;
                let dateFrom = new Date(this.form.startDate);
                let dateTo = new Date(this.form.endDate);
                today.setHours(0, 0, 0, 0)
                dateFrom.setHours(0, 0, 0, 0)
                dateTo.setHours(0, 0, 0, 0)
                if (!this.form.startDate || !this.form.startTime || !this.form.endDate || !this.form.endTime){
                    return false;
                }
                if (dateFrom.getTime() < today.getTime()) return false;
                if (dateTo.getTime() < today.getTime()) return false;
                if (dateTo.getTime() < dateFrom.getTime()) return false;
                if (dateFrom.getTime() == today.getTime()) {
                    if (this.form.startTime <= todayTime) {
                        return false;
                    }
                }
                if (dateTo.getTime() == today.getTime()) {
                    if (this.form.endTime <= todayTime) {
                        return false;
                    }
                }
                if (dateFrom.getTime() == dateTo.getTime()){
                    if (this.form.startTime >= this.form.endTime) return false;
                }
                return true;
            },
            currentDate() {
                let date = new Date();
                let month = date.getMonth() + 1
                if (month < 10) {
                    month = '0' + month
                }
                let day = date.getDate()
                if (day < 10) {
                    day = '0' + day
                }
                return date.getFullYear() + '-' + month + '-' + day
            },
            isFormValid() {
                return this.priceIsValid && this.maxNumOfPeopleIsValid && this.datesAreValid;
            }
        },
		mounted() {
            if (window.sessionStorage.getItem('role')) {
                if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor"){
                    axios.get('http://localhost:8088/adventures/get/' + this.$route.params.id, {
                        headers: {
                            Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                        }
                    }).then((response) => {
                        this.tags = response.data.additionalServices;
                    });
                }
                else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner"){
                    axios.get('http://localhost:8088/ships/get/' + this.$route.params.id, {
                        headers: {
                            Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                        }
                    }).then((response) => {
                        this.tags = response.data.additionalServices;
                    });
                }
                else if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner"){
                    axios.get('http://localhost:8088/retreats/get/' + this.$route.params.id, {
                        headers: {
                            Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                        }
                    }).then((response) => {
                        this.tags = response.data.additionalServices;
                    });
                }
            }
            else {
                router.push("/");
            }
		}
    }
</script>
