<template>
    <div>
        <NavbarAdmin></NavbarAdmin>
        <div class="justify-content-center mx-auto">
            <div class="signUp">
                <div class="app">
                    <div class="vertical-center" style="margin-top: 85px">
                        <div class="inner-block">
                            <div class="signUp">
                                <form @submit.prevent="createNewLoyaltyProgram">
                                    <h3>Add a new loyalty program</h3>
                                    
                                    <div class="form-group required">
                                        <label class="control-label"> Client points per reservation: </label>
                                        <input v-model="form.clientPointsPerReservation" min="1" type="number" class="form-control form-control-lg">
                                    </div>

                                    <div class="form-group required">
                                        <label class="control-label"> Privileged user points per reservation: </label>
                                        <input v-model="form.privilegedUserPointsPerReservation" min="1" type="number" class="form-control form-control-lg">
                                    </div>
                                    
                                    <div class="form-group required">
                                        <label class="control-label"> Required points for silver status: </label>
                                        <input v-model="form.requiredPointsSilver" min="1" type="number" class="form-control form-control-lg">
                                    </div>

                                    <div class="form-group required">
                                        <label class="control-label"> Required points for gold status: </label>
                                        <input v-model="form.requiredPointsGold" min="1" type="number" class="form-control form-control-lg">
                                    </div>     

                                    <div class="form-group required">
                                        <label class="control-label"> Client silver discount: </label>
                                        <input v-model="form.clientBonusSilver" min="1" type="number" placeholder="Number between 1 and 100" class="form-control form-control-lg">
                                    </div>    
                                    
                                    <div class="form-group required">
                                        <label class="control-label"> Privileged user silver discount: </label>
                                        <input v-model="form.privilegedUserBonusSilver" min="1" type="number" placeholder="Number between 1 and 100" class="form-control form-control-lg">
                                    </div>
                                    
                                    <div class="form-group required">
                                        <label class="control-label"> Client gold discount: </label>
                                        <input v-model="form.clientBonusGold" min="1" type="number" placeholder="Number between 1 and 100" class="form-control form-control-lg">
                                    </div>

                                    <div class="form-group required">
                                        <label class="control-label"> Privileged user gold discount: </label>
                                        <input v-model="form.privilegedUserBonusGold" min="1" type="number" placeholder="Number between 1 and 100" class="form-control form-control-lg">
                                    </div>                                                                                                                                                                                                                      
                                    
                                    <div class="form-group">
                                        <button :disabled="$v.form.$invalid || !validData" type="submit" class="btn btn-dark btn-lg btn-block">Create</button>
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
    import { required, maxValue } from 'vuelidate/lib/validators'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import NavbarAdmin from '@/components/NavbarAdmin.vue'
    import router from '@/router'

    
    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)
    const isInteger = (value) => RegExp(/^[1-9]\d*$/).test(value);

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
        computed: {
            validData() {
                if (this.form.requiredPointsSilver === "" || this.form.requiredPointsGold === "" || 
                this.form.clientBonusSilver === "" || this.form.clientBonusGold === "" || this.form.privilegedUserBonusSilver === "" || 
                this.form.privilegedUserBonusGold === "") return false;
                if (parseInt(this.form.requiredPointsSilver) >= parseInt(this.form.requiredPointsGold) || 
                parseInt(this.form.clientBonusSilver) >= parseInt(this.form.clientBonusGold) ||
                parseInt(this.form.privilegedUserBonusSilver) >= parseInt(this.form.privilegedUserBonusGold)) return false;
                return true;
            },
        },
        methods: {
            createNewLoyaltyProgram(){
                axios.post("https://isa-project-tim3.herokuapp.com/loyaltyPrograms/add",
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
                    this.reset();
                })
            },
            reset() {
                this.form.clientPointsPerReservation = ""
                this.form.privilegedUserPointsPerReservation = ""
                this.form.requiredPointsSilver = ""
                this.form.requiredPointsGold = ""
                this.form.clientBonusSilver = ""
                this.form.privilegedUserBonusSilver = ""
                this.form.clientBonusGold = ""
                this.form.privilegedUserBonusGold = ""
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
                    required,
                    isInteger,    
                },
                privilegedUserPointsPerReservation: {
                    required,
                    isInteger,
                },
                requiredPointsSilver: {
                    required,
                    isInteger,
                },
                requiredPointsGold: {
                    required,
                    isInteger,
                },
                clientBonusSilver: {
                    required,
                    maxValue: maxValue(100),
                    isInteger,
                },
                privilegedUserBonusSilver: {
                    required,
                    maxValue: maxValue(100),
                    isInteger,
                },
                clientBonusGold: {
                    required,
                    maxValue: maxValue(100),
                    isInteger,                  
                },
                privilegedUserBonusGold: {
                    required,
                    isInteger,
                    maxValue: maxValue(100),           
                }
            }
        }

    })
</script>