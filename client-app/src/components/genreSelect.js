import React, { useEffect, useState } from "react";
import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import { GENRE_LOV } from "../services/Routes";
import ApiService from "../services/ApiService";

export const GenreSelect = (props) => {

    const [value, setValue] = useState(-1);
    const [genres, setGenres] = useState([]);

    useEffect(() => {
        getGenres();
    }, [])

    const getGenres = () => {
        ApiService.getAPI(GENRE_LOV).then(result => {
            setGenres(result.data);
            console.log(result.data)
        });
    };

    const handleChange = (event) => {
        let newValue = event.target.value;
        setValue(newValue)
        props.onGenreChange && props.onGenreChange(newValue === -1 ? null : newValue)
    }

    return <>
        <FormControl required fullWidth={props.fullWidth}>
            <InputLabel>Genre</InputLabel>
            <Select
                value={value}
                onChange={handleChange}
                fullWidth={props.fullWidth}
                variant="standard"
            >
                <MenuItem value={-1}>All</MenuItem>
                {genres.map(item => (
                    <MenuItem
                        key={item.id}
                        value={item.id}
                    >
                        {item.label}
                    </MenuItem>)
                )}
            </Select>
        </FormControl>
    </>
};