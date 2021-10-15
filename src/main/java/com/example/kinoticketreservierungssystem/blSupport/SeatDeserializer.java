package com.example.kinoticketreservierungssystem.blSupport;

import com.example.kinoticketreservierungssystem.entity.Seat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class SeatDeserializer extends StdDeserializer<Seat> {
        public SeatDeserializer() {
            this(null);
        }

        public SeatDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Seat deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode seatNode = jp.getCodec().readTree(jp);
            Seat seat = new Seat();
            seat.setSeatID(seatNode.get("seatID").textValue());
            seat.setSeatNumber(Integer.parseInt(seatNode.get("seatNumber").textValue()));
            seat.setBooked(Boolean.parseBoolean(seatNode.get("booked").textValue()));
            seat.setRow(seatNode.get("row").charValue());
            seat.setEventRoomInfo(seatNode.get("eventRoomInfo").asText());
            return seat;
        }
    }
}
