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
                                            <input v-model="form.startDate" id="date" type="date" class="form-control form-control-lg"/>
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
                                            <input v-model="form.endDate" id="date" type="date" class="form-control form-control-lg"/>
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
                axios.post("http://localhost:8088/" + service + "/add-action/" + this.$route.params.id,
                    {
                        dateFrom: this.form.startDate,
                        timeFrom: this.form.startTime,
                        dateTo: this.form.endDate,
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
                if (!this.form.startDate || !this.form.startTime || !this.form.endDate || !this.form.endTime){
                    return false;
                }
                if (new Date(this.form.startDate) < today) return false;
                if (new Date(this.form.endDate) < today) return false;
                if (new Date(this.form.startDate).getTime() > new Date(this.form.endDate).getTime()){
                    return false;
                }
                else if (new Date(this.form.startDate).getTime() < new Date(this.form.endDate).getTime()){
                    return true;
                }
                else{
                    if (parseInt(this.form.startTime.split(":")[0]) * 60 + parseInt(this.form.startTime.split(":")[1]) <  parseInt(this.form.endTime.split(":")[0]) * 60 + parseInt(this.form.endTime.split(":")[1])){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
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
