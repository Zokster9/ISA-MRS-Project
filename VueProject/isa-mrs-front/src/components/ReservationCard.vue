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
                    <span class="card-text">Rating</span>
                    <span class="float-end">{{ reservation.userRating }}</span>
                </div>
                <div v-else>
                    <button @click="review" class="btn btn-primary my-3">Make a revision</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['reservation'],
        methods: {
            getDate (date) {
                let origin_date = new Date(date)
                let month = origin_date.getMonth() + 1
                if (month < 10) {
                    month = '0' + month
                }
                return origin_date.getFullYear() + '/' + month + '/' + origin_date.getDate()
            },
            review() {
                this.$emit("review", this.reservation.id);
            }
        }
    }
</script>