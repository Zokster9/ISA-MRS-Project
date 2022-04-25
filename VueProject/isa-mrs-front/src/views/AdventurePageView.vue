<template>
    <div class="d-flex flex-row justify-content-evenly mt-5">
        <div class="adventure-container">
            <h2> {{adventure.name}} </h2>
            <img :src="require(`../assets/${adventure.pictures[0]}`)" class= "adventure-main-pic rounded" alt="Nema slike">
            <div class="mt-3">
                <div class="row m-0">
                    <div class="col-lg-6 mb-3">
                        <div class="card" style="width:100%;">
                            <div class="card-body">
                                <h5 class="card-title"> Instructor biography </h5>
                                <p class="card-text"> {{adventure.instructorBiography}}</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 mb-3">
                        <div class="card" style="width:100%;">
                            <div class="card-body">
                                <h5 class="card-title"> Description </h5>
                                <p class="card-text">  {{adventure.description}} </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 mb-3">
                        <div class="card" style="width:100%;">
                            <div class="card-body">
                                <h5 class="card-title"> Rules of conduct </h5>
                                <template v-for="conduct in adventure.rulesOfConduct">
                                    <p :key="conduct">{{conduct}}</p>
                                </template>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 mb-3">
                        <div class="card" style="width:100%;">
                            <div class="card-body">
                                <h5 class="card-title"> Included fishing equipment </h5>
                                <template v-for="eq in adventure.fishingEquipment">
                                    <p :key="eq">{{eq}}</p>
                                </template>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 mb-3">
                        <div class="card" style="width:100%;">
                            <div class="card-body">
                                <h5 class="card-title"> Price and maximum number of people</h5>
                                <p class="card-text"> Price is  {{adventure.price}} euros.</p>
                                <p class="card-text"> Maximum number of people that can attend the adventure is {{adventure.maxNumOfPeople}}.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 mb-3">
                        <div class="card" style="width:100%;">
                            <div class="card-body">
                                <h5 class="card-title"> Reservation cancellation conditions </h5>
                                <p class="card-text"> {{adventure.reservationCancellationConditions}} </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-3 mt-5">
            <div class="row">
                <template v-for="(picture, index) in adventure.pictures">
                    <div class="col-6" :key="index" style="height:200px;">
                        <img :id="picture" :src="require(`../assets/${picture}`)" alt="nema slike" class="rounded" style="width:235px;height:310px;object-fit:cover">
                    </div>
                </template>
            </div>
            <div class="mt-3">
                <p> Address: {{adventure.country}}, {{adventure.city}}, {{adventure.street}} (MAPA)</p>
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
        name: "AdventurePage",
        data() {
            return {
                adventure: null
            }
        },
        mounted () {
            axios.get("http://localhost:8088/adventures/getAdventure/" + this.$route.params.id).then(response => {
                this.adventure = response.data;
            });
        }
    }
</script>