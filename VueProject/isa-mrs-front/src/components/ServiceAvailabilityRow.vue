#TODO: mozda dodati i sliku servisa u tabelu (pokupljeno je sa back-a)
<template>
    <tr :key="service.id">
        <td class="align-middle text-center">
             <figcaption class="mb-1"> {{service.name}} </figcaption>
            <router-link exact :to="linkToService">
                <img :src="require('@/assets/' + service.pictures[0])" style="width:200px; height:200px" class="rounded">
            </router-link>
        </td>
        <td class="align-middle"> 
            <div class="form-text mb-1" style="color:black;"> Starting date and time: </div>
            <div class="form-outline mb-1"> <input type="date" v-model="startDate" placeholder="Start date" style="width:137px;"> </div>
            <div class="form-outline"> <input type="time" v-model="startTime" placeholder="Start time" style="width:137px;"> </div>
        </td>
        <td class="align-middle">
            <div class="form-text mb-1" style="color:black;"> Ending date and time: </div>
            <div class="form-outline mb-1"> <input type="date" v-model="endDate" placeholder="End date" style="width:137px;"> </div>
            <div class="form-outline"> <input type="time" v-model="endTime" placeholder="End time" style="width:137px;"> </div>
        </td>
        <td class="align-middle"> <button :disabled="!isValid" type="button" class="btn btn-success" style="height:px;" @click="addAvailability(service.id)">Confirm</button></td>
    </tr>
</template>

<script>
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'  
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    export default {
        props: ["service"],
        data() {
            return {
                startDate: "",
                startTime: "",
                endDate: "",
                endTime: "",
                linkToService: ""
            }
        },
        methods: {
            addAvailability(id){
                if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") {
                    this.addRetreatAvailability(id);
				}else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") {
                    this.addShipAvailability(id);
				}else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
                    this.addAdventureAvailability(id);
				}else {
                    alert('Some kind of error happened!');
                }
            },
            addRetreatAvailability(id) {
                axios.post('http://localhost:8088/retreats/add-availability/' + id, {
                    dateFrom: new Date(this.startDate),
                    dateTo: new Date(this.endDate),
                    timeFrom: this.startTime,
                    timeTo: this.endTime
                }, {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                    }
                }).then(() => {
                    alert('Service availability added!');
                }).catch(error => {
                    if (error.response.status === 409) alert("Specified availability exists.");
                    else alert("Could not add service availability");
                })
            },
            addShipAvailability(id) {
                axios.post('http://localhost:8088/ships/add-availability/' + id, {
                    dateFrom: new Date(this.startDate),
                    dateTo: new Date(this.endDate),
                    timeFrom: this.startTime,
                    timeTo: this.endTime
                }, {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                    }
                }).then(() => {
                    alert('Service availability added!');
                }).catch(error => {
                    if (error.response.status === 409) alert("Specified availability exists.");
                    else alert("Could not add service availability");
                })
            },
            addAdventureAvailability(id) {
                axios.post('http://localhost:8088/adventures/add-availability/' + id, {
                    dateFrom: new Date(this.startDate),
                    dateTo: new Date(this.endDate),
                    timeFrom: this.startTime,
                    timeTo: this.endTime
                },{
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                    }
                }).then(() => {
                    alert('Service availability added!');
                }).catch(error => {
                    if (error.response.status === 409) alert("Specified availability exists.");
                    else alert("Could not add service availability");
                })
            }
        },
        computed: {
            isTimeValid() {
                let reg = new RegExp("[0-9]{2}':'[0-9]{2}");
                if (!reg.test(this.startTime)) return false;
                if (!reg.test(this.endTime)) return false; 
                return true;
            },
            isValid(){
                let today = new Date();
                if (!this.startDate || !this.startTime || !this.endDate || !this.endTime){
                    return false;
                }
                if (new Date(this.startDate) < today) return false;
                if (new Date(this.endDate) < today) return false;
                if (new Date(this.startDate).getTime() > new Date(this.endDate).getTime()){
                    return false;
                }
                else if (new Date(this.startDate).getTime() < new Date(this.endDate).getTime()){
                    return true;
                }
                else{
                    if (parseInt(this.startTime.split(":")[0]) * 60 + parseInt(this.startTime.split(":")[1]) <  parseInt(this.endTime.split(":")[0]) * 60 + parseInt(this.endTime.split(":")[1])){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        },
        mounted(){
            if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") {
                this.linkToService = "/retreat/" + this.service.id;
            }else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") {
                this.linkToService = "/ship/" + this.service.id;
            }else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
                this.linkToService = "/adventure/"+ this.service.id;
            }else {
                alert('Some kind of error happened!');
            }
        } 
    }
</script>
    