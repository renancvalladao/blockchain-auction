// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.8.0;

contract Auction {

    struct Requirements {
        string vnfName;
        string vnfType;
        uint8 numCpus;
    }

    Requirements requirements;
    address owner;
    address lowestBidder;
    uint lowestBid;
    uint endTime;
    bool ended;

    constructor(Requirements memory _requirements, uint biddingTime) {
        requirements = _requirements;
        owner = msg.sender;
        endTime = block.timestamp + biddingTime;
    }

    function getRequirements() public view returns (Requirements memory) {
        return requirements;
    }

    function getWinner() public view returns (address) {
        require(ended, "Auction has not ended yet");
        return lowestBidder;
    }

    function placeBid(uint value) external {
        require(!ended, "Auction has already ended");
        require(value > 0, "Bid value must be greater than zero");
        if (value < lowestBid || lowestBid == 0) {
            lowestBid = value;
            lowestBidder = msg.sender;
        }
    }

    function finishAuction() external {
        require(msg.sender == owner, "Only the owner can finish the auction");
        require(block.timestamp >= endTime, "Auction has not ended yet");
        ended = true;
    }

}
