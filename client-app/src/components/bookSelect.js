import React, { useEffect, useState } from "react";
import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import ApiService from "../services/ApiService";
import { BOOK_LOV } from "../services/Routes";

export const BookSelect = (props) => {

    const [value, setValue] = useState(props.defaultValue ? props.defaultValue : -1);
    const [books, setBooks] = useState([]);

    useEffect(() => {
        getBooks();
    }, []);

    const getBooks = () => {
        ApiService.getAPI(BOOK_LOV).then(result => {
            setBooks(result.data);
            console.log(result.data)
        });
    };

    const handleChange = (event) => {
        let newValue = event.target.value;
        setValue(newValue)
        props.onChange && props.onChange(newValue === -1 ? null : newValue)
    }

    return <div style={{marginBottom: '10px'}}>
        <FormControl required fullWidth={props.fullWidth}>
            <InputLabel>Book</InputLabel>
            <Select
                value={value}
                onChange={handleChange}
                fullWidth={props.fullWidth}
                variant="standard"
                disabled={props.disabled}
            >
                {books.map(item => (
                    <MenuItem
                        key={item.id}
                        value={item.id}
                    >
                        {item.label}
                    </MenuItem>)
                )}
            </Select>
        </FormControl>
    </div>
};