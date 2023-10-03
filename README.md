# Custom implementation of Unix command line tool wc!

### Options it support:
1) -c (The number of bytes in each input file is written to the standard output.  This will cancel out any prior usage of the -m option.)
2) -l (The number of lines in each input file is written to the standard output.)
3) -m (The number of characters in each input file is written to the standard output.  If the current locale does not support multibyte characters,
   this is equivalent to the -c option.  This will cancel out any prior usage of the -c option.)
4) -w (The number of words in each input file is written to the standard output.)