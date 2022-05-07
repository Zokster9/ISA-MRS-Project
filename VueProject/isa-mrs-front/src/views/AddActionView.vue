<template>
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
<!--                        TODO: Ja mislim da je trajanje akcije nama zapravo krajnji datum i vreme, a ne string. Pa bi i ovo trebalo dodati u formu.
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
-->
                            <div class="form-group required">
                                <label>Address </label>
                                <input v-model="form.address" id="address" type="text" readonly class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group required">
                                <table>
                                    <tr>
                                        <td>
                                            <label for="city">City </label>
                                            <input v-model="form.city" id="city" type="text" readonly class="form-control form-control-lg"/>
                                        </td>
                                        <td>
                                            <label for="country">Country </label>
                                            <input v-model="form.country" id="country" type="text" readonly class="form-control form-control-lg"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            
                            <div class="form-group required">
                                <label>Price </label>
                                <input v-model.number="form.price" type="number" class="form-control form-control-lg">
                            </div>

                            <!-- TODO: Checkboxovi za tagove servise. Potrebno je prethodno uopstiti u modelu da svi servisi imaju svoje Tagove. -->
                            
                            <div class="form-group">
                                <button @click="addAction"  :disabled="isFormValid == false" type="submit" class="btn btn-dark btn-lg btn-block">Change info</button>
                            </div>
                        </form>
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

    Vue.use(VueAxios, axios)

    export default {
        name: "AddAction",
        data () {
            return {
                form:{
                    startDate: "",
                    startTime: "",
                    //endDate: "",
                    //endTime: "",
                    maxNumOfPeople: "",
                    address: "",
                    city: "",
                    country: "",
                    price: "",
                    tags: []
                },
            }
        },
        methods: {
            priceIsValid() {
                return typeof this.form.price === 'number' && !!this.form.price && this.form.price > 0;
            },
            //TODO: menjaj ovde parametre ako treba
            addAction() {
                if (window.localStorage.getItem("role") === "ROLE_fishingInstructor"){
                    axios.post("http://localhost:8088/adventures/addAction/" + this.$route.params.id,
                    {
                        startDate: this.form.startDate,
                        startTime: this.form.startTime,
                        //endDate: this.form.endDate,
                        //endTime: this.form.endTime,
                        maxNumOfPeople: this.form.maxNumOfPeople,
                        price: this.form.price,
                        addressDTO: {
                            country: this.form.country,
                            city: this.form.city,
                            street: this.form.address,
                        },
                        tags: this.form.tags,
                    },
                    {
                        headers:{
                            Authorization: "Bearer " + window.localStorage.getItem("accessToken")
                        }
                    }).then(() => {
                        alert("Successfully added new action!")
                    }).error(() => {
                        alert("Something went wrong!")
                    })
                }
                else if (window.localStorage.getItem("role") === "ROLE_shipOwner"){
                    //TODO
                }
                else if (window.localStorage.getItem("role") === "ROLE_retreatOwner"){
                    //TODO
                }
            }
        },
        computed: {
            //TODO: Ako treba endDate i endTime, dodati proveru i za to.
            isFormValid(){
                let today = new Date();
                if (!this.form.startDate || !this.form.startTime || !this.form.price || !this.form.tags){
                    return false;
                }
                if (new Date(this.form.startDate) < today) return false;
                if (!this.priceIsValid) return false;
                return true;
            }
        },
		mounted() {
            if (window.localStorage.getItem("role") === "ROLE_fishingInstructor"){
                axios.get("http://localhost:8088/adventures/getAdventure/" + this.$route.params.id, {
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.form.country = response.data.country;
                    this.form.address = response.data.street;
                    this.form.city = response.data.city;
                })
            }
            else if (window.localStorage.getItem("role") === "ROLE_shipOwner"){
                //TODO: Dobavi celokupnu adresu
            }
            else if (window.localStorage.getItem("role") === "ROLE_retreatOwner"){
                //TODO: Dobavi celokupnu adresu
            }
		
		}
    }
</script>
