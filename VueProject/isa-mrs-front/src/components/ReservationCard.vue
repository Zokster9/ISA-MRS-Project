<template>
    <div class="card mb-5 mx-2">
        <img :src="require('@/assets/' + reservation.servicePictures[0])" class="card-img-top" alt="Nema slike">
        <div class="card-body">
            <h5 class="card-title"> {{reservation.serviceName}} </h5>
            <div>
                <span class="card-text">From date</span>
                <span class="float-end">{{ getDate(reservation.fromDate) }}</span>
            </div>
            <div>
                <span class="card-text">To date</span>
                <span class="float-end">{{ getDate(reservation.toDate) }}</span>
            </div>
            <div>
                <span class="card-text">From time</span>
                <span class="float-end">{{ reservation.fromTime }}</span>
            </div>
            <div>
                <span class="card-text">To time</span>
                <span class="float-end">{{ reservation.toTime }}</span>
            </div>
            <div>
                <span class="card-text">Price</span>
                <span class="float-end">{{ reservation.price }}<span>&#8364;</span></span>
            </div>
            <div v-if="reservation.status != 'Pending'">
                <div v-if="reservation.userRating">
                    <span class="card-text">Your rating</span>
                    <span class="float-end"><StarRating :show-rating="false" :increment="0.01" :star-size="24" :inline="true" 
                                :rating="reservation.userRating" :read-only="true"></StarRating> {{ reservation.userRating }}/5
                    </span>
                </div>
                <div v-else>
                    <button @click="review" class="btn btn-primary my-3">Make a revision</button>
                </div>
            </div>
            <div v-else>
                <button @click="cancelReservation" :disabled="!isAvailableForCancellation" class="btn btn-primary my-3">Cancel reservation</button>
            </div>
        </div>
    </div>
</template>

<script>
    import StarRating from 'vue-star-rating'

    export default {
        props: ['reservation'],
        components: {
            StarRating,
        },
        methods: {
            getDate(date) {
                let origin_date = new Date(date)
                let month = origin_date.getMonth() + 1
                let day = origin_date.getDate()
                if (month < 10) {
                    month = '0' + month
                }
                if (day < 10) {
                    day = '0' + day
                }
                return origin_date.getFullYear() + '/' + month + '/' + day
            },
            review() {
                this.$emit("review", this.reservation.id);
            },
            cancelReservation() {
                this.$emit("cancel", this.reservation.id)
            }
        },
        computed: {
            isAvailableForCancellation() {
                const date1 = new Date(this.reservation.fromDate)
                const date2 = new Date()
                const diffTime = Math.abs(date1 - date2);
                const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
                return diffDays >= 3
            }
        }
    }
</script>