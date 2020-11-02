package com.mintdigital.cardscheme.domain.xtraz;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

/**
 * Author : Owolabi Babalola
 * Email *: babs.owolabi@gmail.com
 * date **: November 19, 2016  13:11 PM
 * -------------------------------------------------------------
 */
public interface AppConstant {

	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d yyyy  HH:mm a");
	DateTimeFormatter dateFormatSeperatorStyle = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter dateFormatNoDate = DateTimeFormatter.ofPattern("MMM d yyyy");

	Character NEW_ENTITY = 'N';
	Character DELETE_ENTITY = 'D';
	Character SEEN_ENTITY = 'S';
	Character UPDATED_ENTITY= 'U';
	Character EXPIRED_ENTITY= 'E';



}
