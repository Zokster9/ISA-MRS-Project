<template>
    <div>
        <NavbarAdmin></NavbarAdmin>
        <div class="justify-content-center mx-auto">
            <div class="signUp">
                <div class="app">
                    <div class="vertical-center" style="margin-top: 85px">
                        <div class="inner-block">
                            <div class="signUp">
                                <form @submit.prevent>
                                    <h3>Add a new loyalty program</h3>
                                    
                                    <div class="form-group required">
                                        <label class="control-label"> Client points per reservation: </label>
                                        <input v-model="form.clientPointsPerReservation" type="number" class="form-control form-control-lg">
                                    </div>

                                    <div class="form-group required">
                                        <label class="control-label"> Privileged user points per reservation: </label>
                                        <input v-model="form.privilegedUserPointsPerReservation" type="number" class="form-control form-control-lg">
                                    </div>
                                    
                                    <div class="form-group required">
                                        <label class="control-label"> Required points for silver status: </label>
                                        <input v-model="form.requiredPointsSilver" type="number" class="form-control form-control-lg">
                                    </div>

                                    <div class="form-group required">
                                        <label class="control-label"> Required points for gold status: </label>
                                        <input v-model="form.requiredPointsGold" type="number" class="form-control form-control-lg">
                                    </div>     

                                    <div class="form-group required">
                                        <label class="control-label"> Client silver discount: </label>
                                        <input v-model="form.clientBonusSilver" type="number" placeholder="Number between 1 and 100" class="form-control form-control-lg">
                                    </div>    
                                    
                                    <div class="form-group required">
                                        <label class="control-label"> Privileged user silver discount: </label>
                                        <input v-model="form.privilegedUserBonusSilver" type="number" placeholder="Number between 1 and 100" class="form-control form-control-lg">
                                    </div>
                                    
                                    <div class="form-group required">
                                        <label class="control-label"> Client gold discount: </label>
                                        <input v-model="form.clientBonusGold" type="number" placeholder="Number between 1 and 100" class="form-control form-control-lg">
                                    </div>

                                    <div class="form-group required">
                                        <label class="control-label"> Privileged user gold discount: </label>
                                        <input v-model="form.privilegedUserBonusGold" type="number" placeholder="Number between 1 and 100" class="form-control form-control-lg">
                                    </div>                                                                                                                                                                                                                      
                                    
                                    <div class="form-group">
                                        <button @click="createNewLoyaltyProgram"  :disabled="$v.form.$invalid" type="submit" class="btn btn-dark btn-lg btn-block">Create</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>            
        </div>
    </div>
</template>

<script>
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'
    import { required, minValue, maxValue } from 'vuelidate/lib/validators'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import NavbarAdmin from '@/components/NavbarAdmin.vue'
    import router from '@/router'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    export default({
        name: 'AdminLoyaltyProgramView',
        components: {
            NavbarAdmin
        },
        data(){
            return{
                form: {
                    clientPointsPerReservation: "",
                    privilegedUserPointsPerReservation: "",
                    requiredPointsSilver: "",
                    requiredPointsGold: "",
                    clientBonusSilver: "",
                    privilegedUserBonusSilver: "",
                    clientBonusGold: "",
                    privilegedUserBonusGold: ""
                }
            }
        },
        methods: {
            createNewLoyaltyProgram(){
                axios.post("http://localhost:8088/loyaltyPrograms/add",
                {
                    clientPointsPerReservation: this.form.clientPointsPerReservation,
                    privilegedUserPointsPerReservation: this.form.privilegedUserPointsPerReservation,
                    requiredPointsSilver: this.form.requiredPointsSilver,
                    requiredPointsGold: this.form.requiredPointsGold,
                    clientBonusSilver: this.form.clientBonusSilver,
                    privilegedUserBonusSilver: this.form.privilegedUserBonusSilver,
                    clientBonusGold: this.form.clientBonusGold,
                    privilegedUserBonusGold: this.form.privilegedUserBonusGold
                },
                {
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    alert("Successfully changed loyalty program!")
                    window.location.reload();
                })
            }
        },
        mounted() {
            if (window.sessionStorage.getItem('role') != "ROLE_admin" && window.sessionStorage.getItem("role") != "ROLE_mainAdmin") {
                router.push("/");
            }
        },
        validations: {
            form: {
                clientPointsPerReservation: {
                    required    
                },
                privilegedUserPointsPerReservation: {
                    required
                },
                requiredPointsSilver: {
                    required
                },
                requiredPointsGold: {
                    required
                },
                clientBonusSilver: {
                    required,
                    minValue: minValue(1),
                    maxValue: maxValue(100)
                },
                privilegedUserBonusSilver: {
                    required,
                    minValue: minValue(1),
                    maxValue: maxValue(100)
                },
                clientBonusGold: {
                    required,
                    minValue: minValue(1),
                    maxValue: maxValue(100)                    
                },
                privilegedUserBonusGold: {
                    required,
                    minValue: minValue(1),
                    maxValue: maxValue(100)                    
                }
            }
        }

    })
</script>